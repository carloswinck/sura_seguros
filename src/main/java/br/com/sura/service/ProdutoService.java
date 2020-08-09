package br.com.sura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sura.model.Produto;
import br.com.sura.repository.ProdutoRepository;
import br.com.sura.service.exception.DataIntegrityException;
import br.com.sura.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> pesquisarTodos() {
		return produtoRepository.findAll();
	}

	public Produto pesquisarPorId(Long id) {
		Optional<Produto> retorno = produtoRepository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado com o Id:" + id));
	}
	
	@Transactional
	public Produto inserir(Produto obj) {
		obj.setId(null);
		obj = produtoRepository.save(obj);
		return obj;
	}
	
	public void excluir(Long id) {
		pesquisarPorId(id);
		try {
			produtoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	@Transactional
	public Produto atualizar(Produto obj) {
		Produto objNew = pesquisarPorId(obj.getId());
		updateData(objNew, obj);
		return produtoRepository.save(objNew);
	}
	
	private void updateData(Produto objNew, Produto obj) {
		objNew.setProduto(obj.getProduto());
		objNew.setPreco(obj.getPreco());
		objNew.setDescricao(obj.getDescricao());
		objNew.setQuantidade(obj.getQuantidade());
		objNew.setFoto(obj.getFoto());
	}

}