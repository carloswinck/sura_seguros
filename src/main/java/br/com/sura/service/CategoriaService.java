package br.com.sura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sura.model.Categoria;
import br.com.sura.repository.CategoriaRepository;
import br.com.sura.service.exception.DataIntegrityException;
import br.com.sura.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> pesquisarTodos() {
		return repository.findAll();
	}

	public Categoria pesquisarPorId(Long id) throws ObjectNotFoundException {
		Optional<Categoria> retorno = repository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com o Id:" + id));
	}

	@Transactional
	public Categoria inserir(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public void excluir(Long id) {
		pesquisarPorId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	@Transactional
	public Categoria atualizar(Categoria obj) {
		Categoria objNew = pesquisarPorId(obj.getId());
		updateData(objNew, obj);
		return repository.save(objNew);
	}

	private void updateData(Categoria objNew, Categoria obj) {
		objNew.setCategoria(obj.getCategoria());
	}
}