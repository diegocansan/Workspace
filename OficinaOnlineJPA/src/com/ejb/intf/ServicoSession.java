package com.ejb.intf;

import java.util.List;
import javax.ejb.Local;
import com.ejb.entity.Servico;

@Local
public interface ServicoSession {
	
		void remove(Servico s);
		Servico insere(Servico s);
		Servico altera(Servico s);
		Servico buscaPorId(Long id);
		List<Servico> buscaTodos();
}
