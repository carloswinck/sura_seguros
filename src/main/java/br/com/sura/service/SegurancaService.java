package br.com.sura.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sura.model.Usuario;
import br.com.sura.repository.SegurancaRepository;
import br.com.sura.security.JwtTokenProvider;
import br.com.sura.security.exception.CustomException;

@Service
public class SegurancaService {

	@Autowired
	private SegurancaRepository segurancaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public String signin(String usuario, String senha) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario, senha));
			return jwtTokenProvider.createToken(usuario, segurancaRepository.findByUsuario(usuario).getPapeis());
		} catch (AuthenticationException e) {
			throw new CustomException("Nome de usuário/senha inválidos fornecidos", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(Usuario usuario) {
		if (!segurancaRepository.existsByUsuario(usuario.getUsuario())) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			segurancaRepository.save(usuario);
			return jwtTokenProvider.createToken(usuario.getUsuario(), usuario.getPapeis());
		} else {
			throw new CustomException("O nome de usuário já está em uso", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public void excluir(String usuario) {
		segurancaRepository.deleteByUsuario(usuario);
	}

	public Usuario pesquisar(String usuario) {
		Usuario user = segurancaRepository.findByUsuario(usuario);
		if (user == null) {
			throw new CustomException("O usuário não existe", HttpStatus.NOT_FOUND);
		}
		return user;
	}

	public Usuario whoami(HttpServletRequest req) {
		return segurancaRepository.findByUsuario(jwtTokenProvider.getUsuario(jwtTokenProvider.resolveToken(req)));
	}

}