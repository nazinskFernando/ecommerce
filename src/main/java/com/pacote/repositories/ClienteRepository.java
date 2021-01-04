
package com.pacote.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pacote.domain.Cliente;
import com.pacote.domain.enums.StatusCliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findById(long id);
	
	List<Cliente> findByStatusCliente(StatusCliente statuscliente,  Pageable pageable);
	

	void deleteById(long id);	
	
}
