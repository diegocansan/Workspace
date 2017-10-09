package com.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.entity.Cliente;
import com.ejb.intf.ClienteSession;

@Stateless
public class ClienteSessionBean implements ClienteSession {
	@PersistenceContext(unitName="PUOficinaOnline")
	private EntityManager em;

	@Override
	public void remove(Cliente c) {
		if(em.find(Cliente.class, c.getId()) != null)
			em.remove(em.merge(c));	
	}

	@Override
	public Cliente insere(Cliente c) {
		em.persist(c);
		return c;
	}

	@Override
	public Cliente altera(Cliente c) {
		em.merge(c);
		return c;
	}

	@Override
	public Cliente buscaPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public Cliente buscaPorCpf(String cpf) {
		Query q = em.createNamedQuery("busca.cliente.cpf");
		q.setMaxResults(1);
		q.setParameter("cpf", cpf);	
		return (Cliente) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscaTodos() {
		Query q = em.createNamedQuery("busca.todos.clientes");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscaPorNome(String nome) {
		Query q = em.createNamedQuery("busca.todos.clientes.nome");
		q.setParameter("nome", nome + "%");	
		
		return q.getResultList();
	}

}
