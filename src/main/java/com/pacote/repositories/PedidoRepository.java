
package com.pacote.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pacote.domain.Pedido;
import com.pacote.domain.enums.StatusEntrega;
import java.util.List;
import com.pacote.domain.Cliente;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	Optional<Pedido> findById(long id);
	
	List<Pedido> findByStatusEntrega(StatusEntrega statusentrega);
	
	List<Pedido> findByCliente(Cliente cliente);
	
	void deleteById(Long id);
	
}
