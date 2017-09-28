package com.ejb.intf;

import java.util.List;
import javax.ejb.Local;
import com.ejb.entity.Veiculo;

@Local
public interface VeiculoSession {
	
		void remove(Veiculo v);
		Veiculo insere(Veiculo v);
		Veiculo altera(Veiculo v);
		Veiculo buscaPorId(Long id);
		List<Veiculo> buscaTodos();
}