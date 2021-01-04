
package com.pacote.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pacote.domain.PedidoProduto;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Integer>{

	Optional<PedidoProduto> findById(long id);

//	void deleteById(Long id);	
	
}
