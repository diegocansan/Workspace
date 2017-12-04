package com.services.rest;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejb.entity.Usuario;
import com.ejb.intf.UsuarioSession;
import com.google.gson.Gson;

@Path("/usuarios")
@RequestScoped
public class UsuarioRestService {
		
		@EJB
		private UsuarioSession usuarioSession;
			
		@GET
		@Path("/")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getUsuarios(){
						
			List<Usuario> usuarios = usuarioSession.buscaTodos();
			
			return usuarios != null 
				?Response.ok(new Gson().toJson(usuarios)).build()
				:Response.noContent().build();
		}
		
		@GET
		@Path("/{Id}")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getUsuarioId(@PathParam("Id") Long idUsuario) 
		{			
			Usuario usuario = usuarioSession.buscaPorId(idUsuario);
			
			return usuario != null 
					?Response.ok(new Gson().toJson(usuario)).build()
					:Response.noContent().build();
		}
		
		@GET
		@Path("/porlogin/{login}")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getUsuarioPorLogin(@PathParam("login") String login) 
		{			
			List<Usuario> usuarios = usuarioSession.buscaPorLogin(login);
			
			return usuarios != null 
					?Response.ok(new Gson().toJson(usuarios)).build()
					:Response.noContent().build();
		}
		
		
		@POST
		@Path("/insere")
		@Consumes({MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_JSON})
		public Response insereUsuario(String Json) 
		{					
			if(Json != null)
			{
				Usuario usuario = new Gson().fromJson(Json, Usuario.class);
				usuario = usuarioSession.insere(usuario);
				return Response.ok(new Gson().toJson(usuario)).build();	
			}
			else
				return Response.noContent().build();
		}
		
		@PUT
		@Path("/altera/{Id}")
		@Consumes({MediaType.APPLICATION_JSON})
		public void updateUsuario(@PathParam("Id") Long idUsuario, String Json) 
		{						
			Usuario usuario = usuarioSession.buscaPorId(idUsuario);
			if(usuario != null)
			{
				Usuario usuarioFromJson = new Gson().fromJson(Json, Usuario.class);
				
				usuario.setLogin(usuarioFromJson.getLogin());
				usuario.setSenha(usuarioFromJson.getSenha());
				usuarioSession.altera(usuario);			
			}
		}
		
		@DELETE
		@Path("/delete/{Id}")
		@Consumes({MediaType.APPLICATION_JSON})
		public Response DeleteUsuarioId(@PathParam("Id") Long idUsuario) 
		{			
			Usuario usuario = usuarioSession.buscaPorId(idUsuario);
			
			if(usuario != null)
			{
				usuarioSession.remove(usuario);
				return Response.ok().build();
			}
			else
				return Response.noContent().build();
		}
		
		@POST
		@Path("/auth")
		@Produces({MediaType.APPLICATION_JSON})
		@Consumes({MediaType.APPLICATION_JSON})
		public Response getLogar(String Json){
			
			Usuario usuarioFromJson = new Gson().fromJson(Json, Usuario.class);
						
			Usuario usuario = usuarioSession.login(usuarioFromJson.getLogin(), usuarioFromJson.getSenha());
			
			return usuario != null 
				?Response.ok(new Gson().toJson(usuario)).build()
				:Response.status(404).entity("Usuário ou senha inválidos!").build();
		}
		
	}