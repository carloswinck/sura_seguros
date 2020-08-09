package br.com.sura.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sura.model.Pedido;
import br.com.sura.model.PedidoItens;
import br.com.sura.repository.PedidoRepository;
import br.com.sura.service.exception.DataIntegrityException;
import br.com.sura.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> pesquisarTodos() {
		return pedidoRepository.findAll();
	}

	public Pedido pesquisarPorId(Long id) throws ObjectNotFoundException {
		Optional<Pedido> retorno = pedidoRepository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado com o Id:" + id));
	}
	
	@Transactional
	public Pedido inserir(Pedido pedido) {
		
		BigDecimal total = BigDecimal.ZERO;
		
		Set<PedidoItens> itens = pedido.getItens();
		
		for (PedidoItens pedidoItens : itens) {
			Integer q = pedidoItens.getQuantidade();
			Double p = pedidoItens.getId().getProduto().getPreco();
			
			BigDecimal qunatidade = BigDecimal.valueOf(q);
			BigDecimal preco = BigDecimal.valueOf(p);
			BigDecimal resultado = preco.multiply(qunatidade);
			
			total = total.add(resultado);
			
			Double subTotal = resultado.doubleValue();
			pedidoItens.setSubTotal(subTotal);
			
		}

		pedido.setValor(total.doubleValue());
		
		pedido.setId(null);
		pedido = pedidoRepository.save(pedido);
		return pedido;
	}
	
	public void excluir(Long id) {
		pesquisarPorId(id);
		try {
			pedidoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

}