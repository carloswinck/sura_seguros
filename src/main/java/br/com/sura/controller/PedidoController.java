package br.com.sura.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sura.dto.PedidoDTO;
import br.com.sura.dto.PedidoItensDTO;
import br.com.sura.dto.PedidoResponseDTO;
import br.com.sura.model.Cliente;
import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.PedidoItensPK;
import br.com.sura.model.Produto;
import br.com.sura.service.ClienteService;
import br.com.sura.service.PedidoService;
import br.com.sura.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pedidos")
@Api(tags = "Pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{idPedido}", method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisa pedido por id")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<PedidoResponseDTO> pesquisarPorId(@PathVariable Long idPedido) {
		Pedido pedido = pedidoService.pesquisarPorId(idPedido);
		
		PedidoResponseDTO dto = new PedidoResponseDTO();
		dto.setIdCliente(pedido.getCliente().getId());
		dto.setIdPedido(pedido.getId());
		dto.setSessao(pedido.getSessao());
		dto.setStatus(pedido.getStatus());
		dto.setValor(pedido.getValor());
		
		List<PedidoItensDTO> itens = new ArrayList<PedidoItensDTO>();
		
		Set<PedidoItens> itensRetorno = pedido.getItens();
		
		for (PedidoItens pedidoItens : itensRetorno) {
			PedidoItensDTO itemDto = new PedidoItensDTO();
			itemDto.setIdProduto(pedidoItens.getId().getProduto().getId());
			itemDto.setQuantidade(pedidoItens.getQuantidade());
			itens.add(itemDto);
		}
		
		dto.setItens(itens);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(value = "/clientes/{idClientes}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> inserir(@PathVariable Long idClientes, @RequestBody PedidoDTO dto) {
		
		Pedido pedido = new Pedido();
		
		Cliente cliente = clienteService.pesquisarPorId(idClientes);
		
		pedido.setCliente(cliente);
		
		Set<PedidoItens> itens = new HashSet<PedidoItens>();
		
		pedido.setData(new Date());
		pedido.setSessao(dto.getSessao());
		pedido.setStatus(dto.getStatus());
		
		List<PedidoItensDTO> itensDTO = dto.getItens();
		for (PedidoItensDTO pedidoItensDTO : itensDTO) {
			
			PedidoItens pedidoItens = new PedidoItens();
			PedidoItensPK pk = new PedidoItensPK();
			
			pedidoItens.setQuantidade(pedidoItensDTO.getQuantidade());
			
			Produto produto = produtoService.pesquisarPorId(pedidoItensDTO.getIdProduto());
			
			pk.setProduto(produto);
			pk.setPedido(pedido);
			
			pedidoItens.setId(pk);
			
			itens.add(pedidoItens);
		}

		pedido.setItens(itens);
        pedido = pedidoService.inserir(pedido);
        
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPedido}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
    }
	
	@RequestMapping(value = "/{idPedido}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> excluir(@PathVariable Long idPedido) {
		pedidoService.excluir(idPedido);
		return ResponseEntity.noContent().build();
	}
	
}