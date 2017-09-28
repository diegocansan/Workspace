package com.ejb.intf;

import com.ejb.entity.Produto;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ProdutoSession {
	
		void remove(Produto p);
		Produto insere(Produto p);
		Produto altera(Produto p);
		Produto buscaPorId(Long id);
		List<Produto> buscaTodos();
}