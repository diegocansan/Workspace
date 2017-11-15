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

import com.ejb.entity.StatusOrdemServico;
import com.ejb.intf.StatusOrdemServicoSession;
import com.google.gson.Gson;


@Path("/statusOrdem")
@RequestScoped
public class StatusOrdemServicoRestService {
	
	@EJB
	private StatusOrdemServicoSession statusOrdemSession;
	
	
	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStatus(){
				
		List<StatusOrdemServico> status = statusOrdemSession.buscaTodos();
				
		return status != null 
			?Response.ok(new Gson().toJson(status)).build()
			:Response.noContent().build();
	}
	
	@GET
	@Path("/{Id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStatusId(@PathParam("Id") Long id) 
	{			
		StatusOrdemServico status = statusOrdemSession.buscaPorId(id);
		
		return status != null 
				?Response.ok(new Gson().toJson(status)).build()
				:Response.noContent().build();
	}
	
	@GET
	@Path("/porstatus/{status}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getClientesPorNome(@PathParam("status") String nome) 
	{			
		List<StatusOrdemServico> status = statusOrdemSession.buscaPorStatus(nome);
		
		return status != null 
				?Response.ok(new Gson().toJson(status)).build()
				:Response.noContent().build();
	}
	
	
	@POST
	@Path("/insere")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response insereCliente(String Json) 
	{					
		if(Json != null)
		{
			StatusOrdemServico status = new Gson().fromJson(Json, StatusOrdemServico.class);
			status = statusOrdemSession.insere(status);
			
			return Response.ok(new Gson().toJson(status)).build();	
		}
		else
			return Response.noContent().build();
	}
	
	@PUT
	@Path("/altera/{Id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public void updateCliente(@PathParam("Id") Long id, String Json) 
	{						
		StatusOrdemServico status = statusOrdemSession.buscaPorId(id);
		if(status != null)
		{
			StatusOrdemServico statusFromJson = new Gson().fromJson(Json, StatusOrdemServico.class);
			
			status.setStatus(statusFromJson.getStatus());			
			statusOrdemSession.altera(status);		
			
		}
	}
	
	@DELETE
	@Path("/delete/{Id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response DeleteClienteId(@PathParam("Id") Long id) 
	{			
		StatusOrdemServico status = statusOrdemSession.buscaPorId(id);
		
		if(status != null)
		{
			statusOrdemSession.remove(status);
			return Response.ok().build();
		}
		else
			return Response.noContent().build();
	}

}
