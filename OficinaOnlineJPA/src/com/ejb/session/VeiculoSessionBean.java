package com.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.entity.Veiculo;
import com.ejb.intf.VeiculoSession;

@Stateless
public class VeiculoSessionBean implements VeiculoSession {
	@PersistenceContext(unitName="PUOficinaOnline")
	private EntityManager em;
	
	@Override
	public void remove(Veiculo v) {
		if(em.find(Veiculo.class, v.getId()) != null)
			em.remove(em.merge(v));
	}

	@Override
	public Veiculo insere(Veiculo v) {
		em.persist(v);
		return v;
	}

	@Override
	public Veiculo altera(Veiculo v) {
		em.merge(v);
		return v;
	}

	@Override
	public Veiculo buscaPorId(Long id) {
		return em.find(Veiculo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> buscaTodos() {
		Query q = em.createNamedQuery("busca.todos.veiculos");
		return q.getResultList();
	}

	@Override
	public Veiculo buscaPorPlaca(String placa) {
		Query q = em.createNamedQuery("busca.veiculo.placa");
		q.setMaxResults(1);
		q.setParameter("placa", placa);	
		return (Veiculo) q.getSingleResult();
	}

	@Override
	public void limpaVeiculosCliente(Long id_cliente) {
		Query q = em.createNamedQuery("limpa.veiculos.cliente");
		q.setParameter("cliente_id", id_cliente);	
		q.executeUpdate();
	}
}
