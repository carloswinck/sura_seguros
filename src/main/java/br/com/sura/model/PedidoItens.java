package br.com.sura.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItens implements Serializable {

	private static final long serialVersionUID = 307524498481137739L;

	@EmbeddedId
	private PedidoItensPK id = new PedidoItensPK();
   
    private int quantidade;
    
    private Double subTotal;

}