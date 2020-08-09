package br.com.sura.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Long id;

    private String produto;
    
    private Double preco;
    
    private String descricao;

    private Integer quantidade;
    
    private String foto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

}