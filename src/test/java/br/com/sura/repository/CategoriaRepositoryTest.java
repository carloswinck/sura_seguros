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

import br.com.sura.model.Categoria;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
@DataJpaTest
public class CategoriaRepositoryTest {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Test
	public void testPesquisarTodos() {
		List<Categoria> items = repository.findAll();
		assertEquals(3,items.size());
	}

	@Test
	public void testPesquisarPorId() {
		Categoria categoria = repository.findById(1l).get();
		assertEquals("Limpeza",categoria.getCategoria());
	}
	
	@Test
	public void testInserir() {
		Categoria categoria = new Categoria();
		categoria.setCategoria("Automotivo");
		repository.save(categoria);
		categoria = repository.findByCategoria("Automotivo");
		assertEquals("Automotivo",categoria.getCategoria());
	}
	
	@Test
	public void testAtualizar() {
		Categoria categoria = new Categoria();
		categoria.setCategoria("Automotivo");
		repository.save(categoria);
		categoria = repository.findByCategoria("Automotivo");
		assertEquals("Automotivo",categoria.getCategoria());
		categoria.setCategoria("Pet");
		repository.save(categoria);
		categoria = repository.findByCategoria("Pet");
		assertEquals("Pet",categoria.getCategoria());
	}
	
	@Test
	public void testExcluir() {
		Categoria categoria = new Categoria();
		categoria.setCategoria("Automotivo");
		repository.save(categoria);
		categoria = repository.findByCategoria("Automotivo");
		assertEquals("Automotivo",categoria.getCategoria());
		repository.delete(categoria);
		categoria = repository.findByCategoria("Automotivo");
		assertNull(categoria);
	}

}