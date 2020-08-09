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
import br.com.sura.model.Produto;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
@DataJpaTest
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Test
	public void testPesquisarTodos() {
		List<Produto> items = repository.findAll();
		assertEquals(3,items.size());
	}

	@Test
	public void testPesquisarPorId() {
		Produto produto = repository.findById(2l).get();
		assertEquals("Caneta",produto.getProduto());
	}
	
	@Test
	public void testInserir() {
		Categoria categoria = categoriaRepository.findById(2l).get();
		assertEquals("Papelaria",categoria.getCategoria());
		Produto produto = new Produto();
		produto.setProduto("Borracha");
		produto.setCategoria(categoria);
		repository.save(produto);
		produto = repository.findByProduto("Borracha");
		assertEquals("Borracha",produto.getProduto());
	}
	
	@Test
	public void testAtualizar() {
		Categoria categoria = categoriaRepository.findById(2l).get();
		assertEquals("Papelaria",categoria.getCategoria());
		Produto produto = new Produto();
		produto.setProduto("Borracha");
		produto.setCategoria(categoria);
		repository.save(produto);
		produto = repository.findByProduto("Borracha");
		assertEquals("Borracha",produto.getProduto());
		produto.setProduto("Apontador");
		repository.save(produto);
		produto = repository.findByProduto("Apontador");
		assertEquals("Apontador",produto.getProduto());
	}
	
	@Test
	public void testExcluir() {
		Produto produto = new Produto();
		produto.setProduto("Joaquim");
		repository.save(produto);
		produto = repository.findByProduto("Joaquim");
		assertEquals("Joaquim",produto.getProduto());
		repository.delete(produto);
		produto = repository.findByProduto("Joaquim");
		assertNull(produto);
	}

}