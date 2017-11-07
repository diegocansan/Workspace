package com.ejb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OrdemServico")
@SequenceGenerator(name = "OrdemServico_Sequence", sequenceName = "ordemservico_seq", allocationSize = 0, initialValue = 1)
@NamedQueries({ 
	@NamedQuery(name = "busca.todas", query = "from OrdemServico")
})
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrdemServico_Sequence")
	private Long id;
	
	@Column(name = "data", length = 11, unique = true, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(name = "status", nullable = false)
	private StatusOrdemServico status;
	
	@Column(name = "cliente", nullable = false)
	private Cliente cliente;
	
	@Column(name = "veiculo", nullable = false )
	private Veiculo veiculo;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Servico> servicos;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Produto> produtos;

}
