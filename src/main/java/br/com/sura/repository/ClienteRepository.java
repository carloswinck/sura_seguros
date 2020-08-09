package br.com.sura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sura.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Transactional(readOnly = true)
	public Cliente findByEmail(String email);

	public Cliente findByNome(String nome);
	
}