package com.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.entity.Produto;
import com.ejb.intf.ProdutoSession;

@Stateless
public class ProdutoSessionBean implements ProdutoSession {
	@PersistenceContext(unitName="PUOficinaOnline")
	private EntityManager em;
	
	@Override
	public void remove(Produto p) {
		if(em.find(Produto.class, p.getId()) != null)
			em.remove(em.merge(p));
	}

	@Override
	public Produto insere(Produto p) {
		em.persist(p);
		return p;
	}

	@Override
	public Produto altera(Produto p) {
		em.merge(p);
		return p;
	}

	@Override
	public Produto buscaPorId(Long id) {
		return em.find(Produto.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscaTodos() {
		Query q = em.createNamedQuery("busca.todos.produtos");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscaPorNome(String nome) {
		Query q = em.createNamedQuery("busca.todos.produtos.nome");
		q.setParameter("nome", nome + "%");
		
		System.out.println("Parametro nome: "  + nome + q.getResultList().size());
		
		return q.getResultList();
	}
}
