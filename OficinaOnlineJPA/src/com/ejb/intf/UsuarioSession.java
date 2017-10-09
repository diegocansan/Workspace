package com.ejb.intf;

import java.util.List;

import javax.ejb.Local;

import com.ejb.entity.Usuario;

@Local
public interface UsuarioSession {
	
		void remove(Usuario u);
		Usuario insere(Usuario u);
		Usuario altera(Usuario u);
		Usuario buscaPorId(Long id);
		Usuario login(String login, String senha);
		List<Usuario> buscaTodos();
		List<Usuario> buscaPorLogin(String login);
}