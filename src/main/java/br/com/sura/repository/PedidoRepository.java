package br.com.sura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sura.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}