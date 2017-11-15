package com.services.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplication extends Application {
	HashSet<Object> singletons = new HashSet<Object>();

	public RestApplication() {

	}

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		set.add(UsuarioRestService.class);
		set.add(ProdutoRestService.class);
		set.add(ServicoRestService.class);
		set.add(VeiculoRestService.class);
		set.add(ClienteRestService.class);
		set.add(OrdemServicoRestService.class);
		set.add(StatusOrdemServicoRestService.class);
		
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
