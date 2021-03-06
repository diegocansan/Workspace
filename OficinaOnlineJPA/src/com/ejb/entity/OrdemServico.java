package com.ejb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="OrdemServico")
@SequenceGenerator(name = "OrdemServico_Sequence", sequenceName = "ordemservico_seq", allocationSize = 0, initialValue = 1)
@NamedQueries({ 
	@NamedQuery(name = "busca.todas", query = "from OrdemServico"),
	@NamedQuery(name = "busca.todas.status", query = "from OrdemServico ordem where ordem.status.id = :statusId"),
	@NamedQuery(name = "busca.todas.cliente", query = "from OrdemServico ordem where ordem.cliente.id = :clienteId"),
	@NamedQuery(name = "busca.pendentes.cliente", query = "from OrdemServico ordem where ordem.status.id = 1 and ordem.cliente.id = :clienteId"),
	@NamedQuery(name = "busca.aprovadas.cliente", query = "from OrdemServico ordem where ordem.status.id = 2 and ordem.cliente.id = :clienteId"),
	@NamedQuery(name = "busca.emandamento.cliente", query = "from OrdemServico ordem where ordem.status.id = 3 and ordem.cliente.id = :clienteId"),
	@NamedQuery(name = "busca.concluidas.cliente", query = "from OrdemServico ordem where ordem.status.id = 4 and ordem.cliente.id = :clienteId")
})
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrdemServico_Sequence")
	private Long id;
	
	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToOne
	@JoinColumn(name="statusId")
	private StatusOrdemServico status;
	
	@OneToOne
	@JoinColumn(name="clienteId")
	private Cliente cliente;
	
	@OneToOne
    @JoinColumn(name="veiculoId")
	private Veiculo veiculo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="ordemservico_servicos",
     joinColumns={@JoinColumn(name="ordem_id", 
      referencedColumnName="id")},  
     inverseJoinColumns={@JoinColumn(name="servico_id", 
       referencedColumnName="id")}) 
	private List<Servico> servicos;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="ordemservico_produtos",
     joinColumns={@JoinColumn(name="ordem_id", 
      referencedColumnName="id")},  
     inverseJoinColumns={@JoinColumn(name="produto_id", 
       referencedColumnName="id")}) 
	private List<Produto> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
