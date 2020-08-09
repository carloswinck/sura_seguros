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
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -1287532970881820618L;
    
    private String nome;
    
    private String email;
    
    private String senha;

    private String rua;
    
    private String cidade;
    
    private String bairro;
    
    private String cep;
    
    private String estado;

}