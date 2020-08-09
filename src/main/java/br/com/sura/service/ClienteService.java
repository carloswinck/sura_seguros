package br.com.sura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sura.model.Cliente;
import br.com.sura.repository.ClienteRepository;
import br.com.sura.service.exception.DataIntegrityException;
import br.com.sura.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> pesquisarTodos() {
		return repository.findAll();
	}

	public Cliente pesquisarPorId(Long id) throws ObjectNotFoundException {
		Optional<Cliente> retorno = repository.findById(id);
		return retorno.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado com o Id:" + id));
	}

	@Transactional
	public Cliente inserir(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
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
	public Cliente atualizar(Cliente obj) {
		Cliente objNew = pesquisarPorId(obj.getId());
		updateData(objNew, obj);
		return repository.save(objNew);
	}

	private void updateData(Cliente objNew, Cliente obj) {
		objNew.setNome(obj.getNome());
		objNew.setEmail(obj.getEmail());
	}
	
}
