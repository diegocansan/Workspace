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

import com.ejb.entity.Servico;
import com.ejb.intf.ServicoSession;
import com.google.gson.Gson;

@Path("/servicos")
@RequestScoped
public class ServicoRestService {
		
		@EJB
		private ServicoSession servicoSession;
			
		@GET
		@Path("/")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getServicos(){
						
			List<Servico> servicos = servicoSession.buscaTodos();
			
			return servicos != null 
				?Response.ok(new Gson().toJson(servicos)).build()
				:Response.noContent().build();
		}
		
		@GET
		@Path("/{Id}")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getServicoId(@PathParam("Id") Long id) 
		{			
			Servico servico = servicoSession.buscaPorId(id);
			
			return servico != null 
					?Response.ok(new Gson().toJson(servico)).build()
					:Response.noContent().build();
		}
		
		
		@POST
		@Path("/insere")
		@Consumes({MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_JSON})
		public Response insereServico(String Json) 
		{					
			if(Json != null)
			{
				Servico servico = new Gson().fromJson(Json, Servico.class);
				servico = servicoSession.insere(servico);
				return Response.ok(new Gson().toJson(servico)).build();	
			}
			else
				return Response.noContent().build();
		}
		
		@PUT
		@Path("/altera/{Id}")
		@Consumes({MediaType.APPLICATION_JSON})
		public void updateServico(@PathParam("Id") Long idServico, String Json) 
		{						
			Servico servico = servicoSession.buscaPorId(idServico);
			if(servico != null)
			{
				Servico servicoFromJson = new Gson().fromJson(Json, Servico.class);
				
				servico.setNome(servicoFromJson.getNome());
				servico.setValor(servicoFromJson.getValor());
				servicoSession.altera(servico);			
			}
		}
		
		@DELETE
		@Path("/delete/{Id}")
		@Consumes({MediaType.APPLICATION_JSON})
		public Response DeleteServicoId(@PathParam("Id") Long idServico) 
		{			
			Servico servico = servicoSession.buscaPorId(idServico);
			
			if(servico != null)
			{
				servicoSession.remove(servico);
				return Response.ok().build();
			}
			else
				return Response.noContent().build();
		}
		
	}