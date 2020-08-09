package br.com.sura.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "categorias")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 6267206754521355146L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idCategoria")
    private Long id;
    
	@Column(unique = true)
    private String categoria;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<>();

}