package br.com.sura.controller;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sura.dto.ProdutoDTO;
import br.com.sura.model.Categoria;
import br.com.sura.model.Produto;
import br.com.sura.service.CategoriaService;
import br.com.sura.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/produtos")
@Api(tags = "Produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
    @Autowired
    private ModelMapper modelMapper;

	@RequestMapping(value = "/{idProduto}", method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisar produto por id")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Produto> pesquisarPorId(@PathVariable Long idProduto) {
		Produto retorno = produtoService.pesquisarPorId(idProduto);
		return ResponseEntity.ok().body(retorno);
	}
	
	@RequestMapping(value = "/categorias/{idCategoria}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> inserir(@PathVariable Long idCategoria, @RequestBody ProdutoDTO dto) {
		
		Categoria categoria = categoriaService.pesquisarPorId(idCategoria);
		
		Produto produto = modelMapper.map(dto, Produto.class);
		
		produto.setCategoria(categoria);
		
		Produto obj = produtoService.inserir(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idProduto}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{idProduto}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> excluir(@PathVariable Long idProduto) {
		produtoService.excluir(idProduto);
		return ResponseEntity.noContent().build();
	}

}