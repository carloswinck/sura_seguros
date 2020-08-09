package br.com.sura.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItensDTO implements Serializable {

	private static final long serialVersionUID = 6694409682428850085L;

	private Long idProduto;
   
    private int quantidade;

}