package com.ejb.intf;

import java.util.List;

import javax.ejb.Local;

import com.ejb.entity.OrdemServico;

@Local
public interface OrdemServicoSession {
	
	void remove(OrdemServico o);
	OrdemServico insere(OrdemServico o);
	OrdemServico altera(OrdemServico o);
	OrdemServico buscaPorId(Long id);
	List<OrdemServico> buscaTodas();
	List<OrdemServico> buscaTodasStatus(Long statusId);
}
