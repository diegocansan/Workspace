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
@Table(name = "Veiculos")
@SequenceGenerator(name = "Veiculos_Sequence", sequenceName = "veiculos_seq", allocationSize = 0, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "busca.todos.veiculos", query = "from Veiculo"),
				@NamedQuery(name = "busca.veiculo.placa", query = "from Veiculo v where v.placa = :placa"),
				@NamedQuery(name = "limpa.veiculos.cliente", query = "update Veiculo set cliente_id = null where cliente_id = :cliente_id")
})
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Veiculos_Sequence")
	private Long id;

	@Column(name = "placa", unique = true, length = 7, nullable = true)
	private String placa;

	@Column(name = "modelo", length = 20, nullable = true)
	private String modelo;

	@Column(name = "fabricante", length = 20, nullable = true)
	private String fabricante;

	@Column(name = "ano", nullable = true)
	private Short ano;

	@Column(name = "cor", nullable = true)
	private String cor;
	
	@Column(name = "cliente_id", nullable = true)
	private Long client_id;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Short getAno() {
		return ano;
	}

	public void setAno(Short ano) {
		this.ano = ano;
	}
	
	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(Long client_id) {
		this.client_id = client_id;
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
