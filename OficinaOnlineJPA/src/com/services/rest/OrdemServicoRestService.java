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

import com.ejb.entity.OrdemServico;
import com.ejb.intf.OrdemServicoSession;
import com.google.gson.Gson;

@Path("/ordemServicos")
@RequestScoped
public class OrdemServicoRestService {
	
	@EJB
	private OrdemServicoSession ordemServicoSession;
	
	
	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getOrdens(){
				
		List<OrdemServico> ordens = ordemServicoSession.buscaTodas();
				
		return ordens != null 
			?Response.ok(new Gson().toJson(ordens)).build()
			:Response.noContent().build();
	}
	
	@GET
	@Path("/{Id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getOrdemId(@PathParam("Id") Long id) 
	{			
		OrdemServico ordenServico = ordemServicoSession.buscaPorId(id);
		
		return ordenServico != null 
				?Response.ok(new Gson().toJson(ordenServico)).build()
				:Response.noContent().build();
	}
	
	@GET
	@Path("/status/{Id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getOrdemStatusId(@PathParam("Id") Long id) 
	{			
		List<OrdemServico> ordens = ordemServicoSession.buscaTodasStatus(id);
		
		return ordens != null 
				?Response.ok(new Gson().toJson(ordens)).build()
				:Response.noContent().build();
	}
	
	@POST
	@Path("/insere")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insere(String Json) 
	{					
		if(Json != null)
		{
			OrdemServico ordem = new Gson().fromJson(Json, OrdemServico.class);
			OrdemServico newOrdem = ordemServicoSession.insere(ordem);
					
			return Response.ok(new Gson().toJson(newOrdem)).build();	
		}
		else
			return Response.noContent().build();
	}
	
	@PUT
	@Path("/altera/{Id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public void updateOrdem(@PathParam("Id") Long id, String Json) 
	{						
		OrdemServico ordem = ordemServicoSession.buscaPorId(id);
		if(ordem != null)
		{
			OrdemServico ordemFromJson = new Gson().fromJson(Json, OrdemServico.class);
			
			ordem.setData(ordemFromJson.getData());
			ordem.setStatus(ordemFromJson.getStatus());
			ordem.setServicos(ordemFromJson.getServicos());
			ordem.setProdutos(ordemFromJson.getProdutos());
			
			
			ordemServicoSession.altera(ordem);		
		}
	}
	
	@DELETE
	@Path("/delete/{Id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response DeleteOrdemId(@PathParam("Id") Long id) 
	{			
		OrdemServico ordem = ordemServicoSession.buscaPorId(id);
		
		if(ordem != null)
		{
			ordemServicoSession.remove(ordem);
			return Response.ok().build();
		}
		else
			return Response.noContent().build();
	}
	
	

}
