package com.ejb.intf;

import java.util.List;

import javax.ejb.Local;

import com.ejb.entity.Cliente;

@Local
public interface ClienteSession {
	void remove(Cliente u);
	Cliente insere(Cliente u);
	Cliente altera(Cliente u);
	Cliente buscaPorId(Long id);
	Cliente buscaPorCpf(String cpf);
	List<Cliente> buscaTodos();
	List<Cliente> buscaPorNome(String nome);	
}
