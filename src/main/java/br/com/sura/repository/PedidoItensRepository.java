package br.com.sura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sura.model.PedidoItens;

@Repository
public interface PedidoItensRepository extends JpaRepository<PedidoItens, Long>{

}