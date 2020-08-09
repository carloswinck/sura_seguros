package br.com.sura.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PedidoItensPK implements Serializable {

	private static final long serialVersionUID = -211490662506629917L;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "idPedido")
	private Pedido pedido;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idProduto")
	private Produto produto;

}