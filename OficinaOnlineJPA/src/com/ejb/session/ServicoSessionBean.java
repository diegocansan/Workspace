package com.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.entity.Servico;
import com.ejb.intf.ServicoSession;

@Stateless
public class ServicoSessionBean implements ServicoSession {
	@PersistenceContext(unitName="PUOficinaOnline")
	private EntityManager em;
	
	@Override
	public void remove(Servico s) {
		if(em.find(Servico.class, s.getId()) != null)
			em.remove(em.merge(s));
	}

	@Override
	public Servico insere(Servico s) {
		em.persist(s);
		return s;
	}

	@Override
	public Servico altera(Servico s) {
		em.merge(s);
		return s;
	}

	@Override
	public Servico buscaPorId(Long id) {
		return em.find(Servico.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> buscaTodos() {
		Query q = em.createNamedQuery("busca.todos.servicos");
		return q.getResultList();
	}
}
