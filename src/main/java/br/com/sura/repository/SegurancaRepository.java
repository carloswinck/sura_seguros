package br.com.sura.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sura.model.Usuario;

public interface SegurancaRepository extends JpaRepository<Usuario, Integer> {

  boolean existsByUsuario(String usuario);

  Usuario findByUsuario(String usuario);

  @Transactional
  void deleteByUsuario(String usuario);

}