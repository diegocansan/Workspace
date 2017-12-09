package com.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Usuarios")
@SequenceGenerator(name = "Usuarios_Sequence", sequenceName = "usuarios_seq", allocationSize = 0, initialValue = 1)
@NamedQueries({ 
	@NamedQuery(name = "busca.todos.usuarios", query = "from Usuario"),
	@NamedQuery(name = "busca.todos.usuarios.login", query = "from Usuario u where upper(trim(u.login)) like upper(trim(:login))"),
	@NamedQuery(name = "logar", query = "from Usuario u where upper(trim(u.login)) = upper(trim(:login)) and senha = :senha ")
})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuarios_Sequence")
	private Long id;
	
	@Column(name = "login", length = 60, unique = true, nullable = false)
	private String login;

	@Column(name = "senha", length = 60, nullable = false)
	private String senha;
	
	@OneToOne
	@JoinColumn(name="clienteId", nullable = true)
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
