package com.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.entity.Usuario;
import com.ejb.intf.UsuarioSession;

@Stateless
public class UsuarioSessionBean implements UsuarioSession {
	@PersistenceContext(unitName="PUOficinaOnline")
	private EntityManager em;
	
	@Override
	public void remove(Usuario u) {	
		if(em.find(Usuario.class, u.getId()) != null)
			em.remove(em.merge(u));
	}

	@Override
	public Usuario insere(Usuario u) {
		em.persist(u);
		return u;
	}

	@Override
	public Usuario altera(Usuario u) {
		em.merge(u);
		return u;
	}

	@Override
	public Usuario buscaPorId(Long id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public Usuario login(String login, String senha) {	
		Query q = em.createNamedQuery("logar");
		q.setMaxResults(1);
		q.setParameter("login", login);	
		q.setParameter("senha", senha);	
		return (Usuario) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscaTodos() {
		Query q = em.createNamedQuery("busca.todos.usuarios");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscaPorLogin(String login) {
		Query q = em.createNamedQuery("busca.todos.usuarios.login");
		q.setParameter("login", login + "%");	
		
		return q.getResultList();
	}
}
