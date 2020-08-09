package br.com.sura.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class PedidoResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 6175898605901290351L;
	
	private Long idPedido;
	
	private Long idCliente;
    
    private String status;
    
    private String sessao;
    
    private Double valor;

	private List<PedidoItensDTO> itens = new ArrayList<PedidoItensDTO>();

}