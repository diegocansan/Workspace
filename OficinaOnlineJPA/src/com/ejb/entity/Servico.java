package com.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="Servicos")
@SequenceGenerator(name = "Servicos_Sequence", sequenceName = "servicos_seq", allocationSize = 0, initialValue = 1)
@NamedQueries({ 
	@NamedQuery(name = "busca.todos.servicos", query = "from Servico"),
	@NamedQuery(name = "busca.todos.servicos.nome", query = "from Servico s where upper(trim(s.nome)) like upper(trim(:nome))")
})
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Servicos_Sequence")
	private Long id;
	
	@Column(name = "nome", length = 60, nullable = true)
	private String nome;

	@Column(name = "valor", nullable = true)
	private float valor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
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
		Servico other = (Servico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
