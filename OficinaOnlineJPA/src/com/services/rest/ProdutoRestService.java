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

import com.ejb.entity.Produto;
import com.ejb.intf.ProdutoSession;
import com.google.gson.Gson;

@Path("/produtos")
@RequestScoped
public class ProdutoRestService {
		
		@EJB
		private ProdutoSession produtoSession;
			
		@GET
		@Path("/")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getProdutos(){
						
			List<Produto> produtos = produtoSession.buscaTodos();
			
			return produtos != null 
				?Response.ok(new Gson().toJson(produtos)).build()
				:Response.noContent().build();
		}
		
		@GET
		@Path("/{Id}")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getProdutoId(@PathParam("Id") Long idProduto) 
		{			
			Produto produto = produtoSession.buscaPorId(idProduto);
			
			return produto != null 
					?Response.ok(new Gson().toJson(produto)).build()
					:Response.noContent().build();
		}
		
		@GET
		@Path("/pornome/{nome}")
		@Produces({MediaType.APPLICATION_JSON})
		public Response getProdutosPorNome(@PathParam("nome") String nome) 
		{			
			List<Produto> produtos = produtoSession.buscaPorNome(nome);
			
			return produtos != null 
					?Response.ok(new Gson().toJson(produtos)).build()
					:Response.noContent().build();
		}
		
		
		@POST
		@Path("/insere")
		@Consumes({MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_JSON})
		public Response insereProduto(String Json) 
		{					
			if(Json != null)
			{
				Produto produto = new Gson().fromJson(Json, Produto.class);
				produto = produtoSession.insere(produto);
				return Response.ok(new Gson().toJson(produto)).build();	
			}
			else
				return Response.noContent().build();
		}
		
		@PUT
		@Path("/altera/{Id}")
		@Consumes({MediaType.APPLICATION_JSON})
		public void updateProduto(@PathParam("Id") Long idProduto, String Json) 
		{						
			Produto produto = produtoSession.buscaPorId(idProduto);
			if(produto != null)
			{
				Produto produtoFromJson = new Gson().fromJson(Json, Produto.class);
				
				produto.setNome(produtoFromJson.getNome());
				produto.setValor(produtoFromJson.getValor());
				produtoSession.altera(produto);			
			}
		}
		
		@DELETE
		@Path("/delete/{Id}")
		@Consumes({MediaType.APPLICATION_JSON})
		public Response DeleteProdutoId(@PathParam("Id") Long idProduto) 
		{			
			Produto produto = produtoSession.buscaPorId(idProduto);
			
			if(produto != null)
			{
				produtoSession.remove(produto);
				return Response.ok().build();
			}
			else
				return Response.noContent().build();
		}
		
	}