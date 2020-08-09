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
public class PedidoDTO implements Serializable {
	
	private static final long serialVersionUID = 6175898605901290351L;
    
    private String status;
    
    private String sessao;
	
	private List<PedidoItensDTO> itens = new ArrayList<PedidoItensDTO>();

}