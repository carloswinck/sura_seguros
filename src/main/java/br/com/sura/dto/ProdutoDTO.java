package br.com.sura.dto;

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
public class ProdutoDTO {

    private String produto;
    
    private Double preco;
    
    private String descricao;

    private Integer quantidade;
    
    private String foto;

}