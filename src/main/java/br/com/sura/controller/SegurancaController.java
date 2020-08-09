package br.com.sura.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sura.dto.UsuarioDataDTO;
import br.com.sura.dto.UsuariorResponseDTO;
import br.com.sura.model.Usuario;
import br.com.sura.service.SegurancaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/seguranca")
@Api(tags = "Segurança")
public class SegurancaController {

	@Autowired
	private SegurancaService segurancaService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/signin")
	@ApiOperation(value = "Logar")
	public String login(@RequestParam String usuario, @RequestParam String senha) {
		return segurancaService.signin(usuario, senha);
	}

	@PostMapping("/signup")
	@ApiOperation(value = "Deslogar")
	public String signup(@RequestBody UsuarioDataDTO usuario) {
		return segurancaService.signup(modelMapper.map(usuario, Usuario.class));
	}

	@DeleteMapping(value = "/{usuario}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "Excluir usuário", authorizations = { @Authorization(value = "apiKey") })
	public String delete(@PathVariable String usuario) {
		segurancaService.excluir(usuario);
		return usuario;
	}

	@GetMapping(value = "/{usuario}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "Pesquisar usuário", response = UsuariorResponseDTO.class, authorizations = {
			@Authorization(value = "apiKey") })
	public UsuariorResponseDTO search(@PathVariable String usuario) {
		return modelMapper.map(segurancaService.pesquisar(usuario), UsuariorResponseDTO.class);
	}

	@GetMapping(value = "/whoami")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "Quem eu sou?", response = UsuariorResponseDTO.class, authorizations = {
			@Authorization(value = "apiKey") })
	public UsuariorResponseDTO whoami(HttpServletRequest req) {
		return modelMapper.map(segurancaService.whoami(req), UsuariorResponseDTO.class);
	}

}
