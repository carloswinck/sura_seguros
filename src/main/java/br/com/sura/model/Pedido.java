package br.com.sura.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pedidos")
public class Pedido implements Serializable {
	
	static final long serialVersionUID = -3818985303410053204L;
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "idPedido")
    private Long id;

    private Date data;
    
    private String status;
    
    private String sessao;
    
    private Double valor;
    
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<PedidoItens> itens = new HashSet<PedidoItens>();

}