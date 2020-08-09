package br.com.sura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sura.model.Usuario;
import br.com.sura.repository.SegurancaRepository;

@Service
public class UsuarioDetalhes implements UserDetailsService {

  @Autowired
  private SegurancaRepository segurancaRepository;

  @Override
  public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
    final Usuario user = segurancaRepository.findByUsuario(usuario);

    if (user == null) {
      throw new UsernameNotFoundException("Usuário '" + usuario + "' não encontrado");
    }

    return org.springframework.security.core.userdetails.User
        .withUsername(usuario)
        .password(user.getSenha())
        .authorities(user.getPapeis())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)	
        .build();
  }

}