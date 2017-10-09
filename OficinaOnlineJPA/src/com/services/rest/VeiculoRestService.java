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

import com.ejb.entity.Veiculo;
import com.ejb.intf.VeiculoSession;
import com.google.gson.Gson;

@Path("/veiculos")
@RequestScoped
public class VeiculoRestService {

	@EJB
	private VeiculoSession veiculoSession;

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVeiculos() {

		List<Veiculo> veiculos = veiculoSession.buscaTodos();

		return veiculos != null ? Response.ok(new Gson().toJson(veiculos)).build() : Response.noContent().build();
	}

	@GET
	@Path("/{Id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVeiculoId(@PathParam("Id") Long id) {
		Veiculo veiculo = veiculoSession.buscaPorId(id);

		return veiculo != null ? Response.ok(new Gson().toJson(veiculo)).build() : Response.noContent().build();
	}
	
	@GET
	@Path("/placa/{placa}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVeiculoId(@PathParam("placa") String placa) {
		Veiculo veiculo = veiculoSession.buscaPorPlaca(placa);

		return veiculo != null ? Response.ok(new Gson().toJson(veiculo)).build() : Response.noContent().build();
	}

	@POST
	@Path("/insere")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insereVeiculo(String Json) {
		if (Json != null) {
			Veiculo veiculo = new Gson().fromJson(Json, Veiculo.class);
			veiculo = veiculoSession.insere(veiculo);
			return Response.ok(new Gson().toJson(veiculo)).build();
		} else
			return Response.noContent().build();
	}

	@PUT
	@Path("/altera/{Id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void updateVeiculo(@PathParam("Id") Long idVeiculo, String Json) {
		Veiculo veiculo = veiculoSession.buscaPorId(idVeiculo);
		if (veiculo != null) {
			Veiculo veiculoFromJson = new Gson().fromJson(Json, Veiculo.class);

			veiculo.setFabricante(veiculoFromJson.getFabricante());
			veiculo.setCor(veiculoFromJson.getCor());
			veiculo.setAno(veiculoFromJson.getAno());
			veiculoSession.altera(veiculo);
		}
	}

	@DELETE
	@Path("/delete/{Id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response DeleteVeiculoId(@PathParam("Id") Long idVeiculo) {
		Veiculo veiculo = veiculoSession.buscaPorId(idVeiculo);

		if (veiculo != null) {
			veiculoSession.remove(veiculo);
			return Response.ok().build();
		} else
			return Response.noContent().build();
	}

}
