package br.com.sura.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sura.model.Categoria;
import br.com.sura.model.Cliente;
import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.model.PedidoItensPK;
import br.com.sura.model.Produto;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
@DataJpaTest
public class PedidoRepositoryTest {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	public void testCriarPesquisarAlterarExcluir() {
		
		Categoria categoria = categoriaRepository.findById(2l).get();
		assertEquals("Papelaria", categoria.getCategoria());
		
		Produto produto = new Produto();
		produto.setProduto("Borracha");
		produto.setCategoria(categoria);
		produto.setPreco(2.00d);
		produtoRepository.save(produto);
		produto = produtoRepository.findByProduto("Borracha");
		assertEquals("Borracha", produto.getProduto());
		
		Cliente cliente = new Cliente();
		cliente.setNome("Joaquim");
		clienteRepository.save(cliente);
		cliente = clienteRepository.findByNome("Joaquim");
		assertEquals("Joaquim", cliente.getNome());
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		
		PedidoItens item = new PedidoItens();
		item.setQuantidade(100);
		
		PedidoItensPK pk = new PedidoItensPK();
		pk.setProduto(produto);
		pk.setPedido(pedido);
		
		item.setId(pk);
		
		Set<PedidoItens> itens = new HashSet<PedidoItens>();
		itens.add(item);
		pedido.setItens(itens);
		repository.save(pedido);
		
		pedido = repository.findById(1l).get();
		assertEquals("Borracha", pedido.getItens().stream().findFirst().get().getId().getProduto().getProduto());
		
		produto = new Produto();
		produto.setProduto("Lapiseira");
		produto.setCategoria(categoria);
		produto.setPreco(9.00d);
		produtoRepository.save(produto);
		produto = produtoRepository.findByProduto("Lapiseira");
		assertEquals("Lapiseira", produto.getProduto());
		
		item = new PedidoItens();
		item.setQuantidade(50);
		
		pk = new PedidoItensPK();
		pk.setProduto(produto);
		pk.setPedido(pedido);
		
		item.setId(pk);
		
		itens.add(item);
		
		pedido.setItens(itens);
		
		repository.save(pedido);
		assertEquals(2, pedido.getItens().size());
		
		Pedido retorno = repository.findById(1l).get();
		repository.delete(retorno);
		
		Optional<Pedido> retornoDeletado = repository.findById(1l);
		assertEquals(false, retornoDeletado.isPresent());

	}
	
}