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
	List<OrdemServico> buscaTodasCliente(Long clienteId);
	List<OrdemServico> buscaPendentesCliente(Long clienteId);
	List<OrdemServico> buscaAprovadasCliente(Long clienteId);
	List<OrdemServico> buscaEmAndamentoCliente(Long clienteId);
	List<OrdemServico> buscaConcluidasCliente(Long clienteId);
}
