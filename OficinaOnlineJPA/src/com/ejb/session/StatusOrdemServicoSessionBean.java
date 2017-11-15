package com.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.entity.StatusOrdemServico;
import com.ejb.intf.StatusOrdemServicoSession;

@Stateless
public class StatusOrdemServicoSessionBean implements StatusOrdemServicoSession {
	@PersistenceContext(unitName="PUOficinaOnline")
	private EntityManager em;

	@Override
	public void remove(StatusOrdemServico s) {
		if(em.find(StatusOrdemServico.class, s.getId()) != null)
			em.remove(em.merge(s));	
		
	}

	@Override
	public StatusOrdemServico insere(StatusOrdemServico s) {
		em.persist(s);
		return s;
	}

	@Override
	public StatusOrdemServico altera(StatusOrdemServico s) {
		em.merge(s);
		return s;
	}

	@Override
	public StatusOrdemServico buscaPorId(Long id) {
		return em.find(StatusOrdemServico.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatusOrdemServico> buscaTodos() {
		Query q = em.createNamedQuery("busca.todos");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatusOrdemServico> buscaPorStatus(String status) {
		Query q = em.createNamedQuery("busca.todos.status");
		q.setParameter("status", status + "%");	
		
		return q.getResultList();
	}

}
