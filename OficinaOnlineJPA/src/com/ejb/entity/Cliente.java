package com.ejb.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Clientes")
@SequenceGenerator(name = "Clientes_Sequence", sequenceName = "clientes_seq", allocationSize = 0, initialValue = 1)
@NamedQueries({ 
	@NamedQuery(name = "busca.todos.clientes", query = "from Cliente"),
	@NamedQuery(name = "busca.todos.clientes.nome", query = "from Cliente c where upper(trim(c.nome)) like upper(trim(:nome))"),
	@NamedQuery(name = "busca.cliente.cpf", query = "from Cliente c where c.cpf = :cpf")
})
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Clientes_Sequence")
	private Long id;
	
	@Column(name = "cpf", length = 11, unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "nome", length = 60, nullable = false)
	private String nome;

	@Column(name = "email", length = 60, nullable = false)
	private String email;
	
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", nullable = true)
	private List<Veiculo> veiculos;
	

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
