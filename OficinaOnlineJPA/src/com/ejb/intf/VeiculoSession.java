package com.ejb.intf;

import java.util.List;
import javax.ejb.Local;
import com.ejb.entity.Veiculo;

@Local
public interface VeiculoSession {
	
		void remove(Veiculo v);
		void limpaVeiculosCliente(Long id_cliente);
		Veiculo insere(Veiculo v);
		Veiculo altera(Veiculo v);
		Veiculo buscaPorId(Long id);
		Veiculo buscaPorPlaca(String placa);
		List<Veiculo> buscaTodos();
}