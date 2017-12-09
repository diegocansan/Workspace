package com.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.entity.OrdemServico;
import com.ejb.intf.OrdemServicoSession;

@Stateless
public class OrdemServicoSessionBean implements OrdemServicoSession {
	@PersistenceContext(unitName="PUOficinaOnline")
	private EntityManager em;

	@Override
	public void remove(OrdemServico o) {
		if(em.find(OrdemServico.class, o.getId()) != null)
			em.remove(em.merge(o));	
		
	}

	@Override
	public OrdemServico insere(OrdemServico o) {
		em.persist(o);
		return o;
	}

	@Override
	public OrdemServico altera(OrdemServico o) {
		em.merge(o);
		return o;
	}

	@Override
	public OrdemServico buscaPorId(Long id) {
		return em.find(OrdemServico.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> buscaTodas() {
		Query q = em.createNamedQuery("busca.todas");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> buscaTodasStatus(Long statusId) {
		Query q = em.createNamedQuery("busca.todas.status");
		q.setParameter("statusId", statusId);	
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> buscaTodasCliente(Long clienteId) {
		Query q = em.createNamedQuery("busca.todas.cliente");
		q.setParameter("clienteId", clienteId);	
		return q.getResultList();
	}
	
	
	
	
	
	

}
