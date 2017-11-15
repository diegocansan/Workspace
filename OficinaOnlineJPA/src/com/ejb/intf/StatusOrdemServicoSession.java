package com.ejb.intf;

import java.util.List;

import javax.ejb.Local;

import com.ejb.entity.StatusOrdemServico;

@Local
public interface StatusOrdemServicoSession {
	void remove(StatusOrdemServico s);
	StatusOrdemServico insere(StatusOrdemServico s);
	StatusOrdemServico altera(StatusOrdemServico s);
	StatusOrdemServico buscaPorId(Long id);
	List<StatusOrdemServico> buscaTodos();
	List<StatusOrdemServico> buscaPorStatus(String nome);
}
