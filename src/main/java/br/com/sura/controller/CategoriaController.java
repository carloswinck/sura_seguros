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

import br.com.sura.dto.CategoriaDTO;
import br.com.sura.model.Categoria;
import br.com.sura.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/categorias")
@Api(tags = "Categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
    @Autowired
    private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisar categorias")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<List<Categoria>> pesquisarTodos() {
		List<Categoria> list = categoriaService.pesquisarTodos();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{idCategoria}", method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisar categoria por id")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Categoria> pesquisarPorId(@PathVariable Long idCategoria) {
		Categoria retorno;
		retorno = categoriaService.pesquisarPorId(idCategoria);
		return ResponseEntity.ok().body(retorno);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Inserir nova categoria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> inserir(@RequestBody CategoriaDTO dto) {
		Categoria categoria = modelMapper.map(dto, Categoria.class);
		Categoria obj = categoriaService.inserir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCategoria}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{idCategoria}", method = RequestMethod.PUT)
	@ApiOperation(value = "Atualizar categoria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> atualizar(@RequestBody CategoriaDTO dto, @PathVariable Long idCategoria) {
		Categoria categoria = modelMapper.map(dto, Categoria.class);
		categoria.setId(idCategoria);
		categoriaService.atualizar(categoria);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/{idCategoria}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Excluir categoria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> excluir(@PathVariable Long idCategoria) {
		categoriaService.excluir(idCategoria);
		return ResponseEntity.noContent().build();
	}

}