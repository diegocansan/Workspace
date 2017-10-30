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

import com.ejb.entity.Cliente;
import com.ejb.entity.Veiculo;
import com.ejb.intf.ClienteSession;
import com.ejb.intf.VeiculoSession;
import com.google.gson.Gson;

@Path("/clientes")
@RequestScoped
public class ClienteRestService {
	
	@EJB
	private ClienteSession clienteSession;
	@EJB
	private VeiculoSession veiculoSession;
	
	
	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getClientes(){
				
		List<Cliente> clientes = clienteSession.buscaTodos();
				
		return clientes != null 
			?Response.ok(new Gson().toJson(clientes)).build()
			:Response.noContent().build();
	}
	
	@GET
	@Path("/{Id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getClienteId(@PathParam("Id") Long idUsuario) 
	{			
		Cliente cliente = clienteSession.buscaPorId(idUsuario);
		
		return cliente != null 
				?Response.ok(new Gson().toJson(cliente)).build()
				:Response.noContent().build();
	}
	
	@GET
	@Path("/pornome/{nome}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getClientesPorNome(@PathParam("nome") String nome) 
	{			
		List<Cliente> clientes = clienteSession.buscaPorNome(nome);
		
		return clientes != null 
				?Response.ok(new Gson().toJson(clientes)).build()
				:Response.noContent().build();
	}
	@GET
	@Path("/porcpf/{cpf}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getClientesPorCpf(@PathParam("cpf") String cpf) 
	{			
		Cliente cliente = clienteSession.buscaPorCpf(cpf);
		
		return cliente != null 
				?Response.ok(new Gson().toJson(cliente)).build()
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
			Cliente cliente = new Gson().fromJson(Json, Cliente.class);
			Cliente newcliente = clienteSession.insere(cliente);
			
			for (Veiculo v : cliente.getVeiculos()){
				v.setClient_id(newcliente.getId());
				veiculoSession.altera(v);
			}	
			
			return Response.ok(new Gson().toJson(newcliente)).build();	
		}
		else
			return Response.noContent().build();
	}
	
	@PUT
	@Path("/altera/{Id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public void updateCliente(@PathParam("Id") Long idCliente, String Json) 
	{						
		Cliente cliente = clienteSession.buscaPorId(idCliente);
		if(cliente != null)
		{
			Cliente clienteFromJson = new Gson().fromJson(Json, Cliente.class);
			
			cliente.setNome(clienteFromJson.getNome());
			cliente.setCpf(clienteFromJson.getCpf());
			cliente.setEmail(clienteFromJson.getEmail());
			cliente.setTelefone(clienteFromJson.getTelefone());
			
			clienteSession.altera(cliente);		
			
			veiculoSession.limpaVeiculosCliente(cliente.getId());
			
			for (Veiculo v : clienteFromJson.getVeiculos()){
				v.setClient_id(idCliente);
				veiculoSession.altera(v);
			}	
		}
	}
	
	@DELETE
	@Path("/delete/{Id}")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response DeleteClienteId(@PathParam("Id") Long idCliente) 
	{			
		Cliente cliente = clienteSession.buscaPorId(idCliente);
		
		if(cliente != null)
		{
			clienteSession.remove(cliente);
			return Response.ok().build();
		}
		else
			return Response.noContent().build();
	}

}
