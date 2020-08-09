package br.com.sura.controller;

import java.net.URI;
import java.util.List;

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

import br.com.sura.dto.ClienteDTO;
import br.com.sura.model.Cliente;
import br.com.sura.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/clientes")
@Api(tags = "Clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
    @Autowired
    private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisar clientes")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<List<Cliente>> pesquisarTodos() {
		List<Cliente> list = service.pesquisarTodos();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{idCliente}", method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisar cliente por id")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Cliente> pesquisarPorIdId(@PathVariable Long idCliente) {
		Cliente retorno = service.pesquisarPorId(idCliente);
		return ResponseEntity.ok().body(retorno);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Inserir cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> inserir(@RequestBody ClienteDTO dto) {
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		Cliente obj = service.inserir(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{idCliente}", method = RequestMethod.PUT)
	@ApiOperation(value = "Atualizar cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> atualizar(@RequestBody ClienteDTO dto, @PathVariable Long idCliente) {
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		cliente.setId(idCliente);
		service.atualizar(cliente);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/{idCliente}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Excluir cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> excluir(@PathVariable Long idCliente) {
		service.excluir(idCliente);
		return ResponseEntity.noContent().build();
	}

}