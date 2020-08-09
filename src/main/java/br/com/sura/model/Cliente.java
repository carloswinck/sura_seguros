package br.com.sura.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -1063569439780665906L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idCliente")
    private Long id;
    
    private String nome;
    
    private String email;
    
    private String senha;

    private String rua;
    
    private String cidade;
    
    private String bairro;
    
    private String cep;
    
    private String estado;
    
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

}