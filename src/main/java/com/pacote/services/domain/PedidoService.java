package com.pacote.services.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pacote.DTO.PedidoDTO;
import com.pacote.DTO.PedidoDTOCadastrar;
import com.pacote.domain.Cliente;
import com.pacote.domain.Pedido;
import com.pacote.domain.enums.StatusEntrega;
import com.pacote.repositories.PedidoRepository;
import com.pacote.services.exceptions.DataIntegrityException;
import com.pacote.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ClienteService clienteService;
	

	public Pedido find(long id) {

		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:" + id + ", Tipo: " + Pedido.class.getName()));
	}
	

	public Pedido insert(Pedido obj) {
		obj.setId(0);
		return repo.save(obj);
	}

	public Pedido update(Pedido obj) {
//		OrdemServico newObj = find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posível excluir uma categoria que possui produtos");
		}
	}

	public List<Pedido> findAll() {
		return repo.findAll();
	}
	
	public List<Pedido> findStatus(String status) {
		StatusEntrega statusPedido = null;
		switch(status.toLowerCase()) {
		case "andamento":
			statusPedido = StatusEntrega.ANDAMENTO;
		break;
		case "cancelado":
			statusPedido = StatusEntrega.CANCELADO;
		break;
		case "entregue":
			statusPedido = StatusEntrega.ENTREGUE;
		break;
		case "pendente":
			statusPedido = StatusEntrega.PENDENTE;
		break;
		}
		return repo.findByStatusEntrega(statusPedido);
	}

	public Page<Pedido> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	

	public Pedido fromDTO(PedidoDTO objDto) {
		return new Pedido(objDto.getId(), 								
						objDto.getValorTotal(),
						objDto.getDataCadastro(),
						objDto.getStatusEntrega(),
						objDto.getCliente()
						);
	}
	public Pedido fromDTOCreate(PedidoDTOCadastrar objDto) {
		Cliente cliente = clienteService.find(objDto.getIdCliente());
		return new Pedido(objDto.getId(), 								
						objDto.getValorTotal(),
						objDto.getDataCadastro(),
						objDto.getStatusEntrega(),
						cliente
						);
	}
}

