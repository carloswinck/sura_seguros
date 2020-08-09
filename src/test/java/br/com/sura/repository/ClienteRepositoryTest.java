package br.com.sura.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sura.model.Cliente;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
@DataJpaTest
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository repository;
	
	@Test
	public void testPesquisarTodos() {
		List<Cliente> items = repository.findAll();
		assertEquals(3,items.size());
	}

	@Test
	public void testPesquisarPorId() {
		Cliente cliente = repository.findById(3l).get();
		assertEquals("Pedro",cliente.getNome());
	}
	
	@Test
	public void testInserir() {
		Cliente cliente = new Cliente();
		cliente.setNome("Joaquim");
		repository.save(cliente);
		cliente = repository.findByNome("Joaquim");
		assertEquals("Joaquim",cliente.getNome());
	}
	
	@Test
	public void testAtualizar() {
		Cliente cliente = new Cliente();
		cliente.setNome("Joaquim");
		repository.save(cliente);
		cliente = repository.findByNome("Joaquim");
		assertEquals("Joaquim",cliente.getNome());
		cliente.setNome("Paulo");
		repository.save(cliente);
		cliente = repository.findByNome("Paulo");
		assertEquals("Paulo",cliente.getNome());
	}
	
	@Test
	public void testExcluir() {
		Cliente cliente = new Cliente();
		cliente.setNome("Joaquim");
		repository.save(cliente);
		cliente = repository.findByNome("Joaquim");
		assertEquals("Joaquim",cliente.getNome());
		repository.delete(cliente);
		cliente = repository.findByNome("Joaquim");
		assertNull(cliente);
	}

}