package com.pacote.services.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pacote.DTO.PedidoProdutoDTO;
import com.pacote.domain.PedidoProduto;
import com.pacote.repositories.PedidoProdutoRepository;
import com.pacote.services.exceptions.DataIntegrityException;
import com.pacote.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoProdutoService {

	@Autowired
	private PedidoProdutoRepository repo;
	

	public PedidoProduto find(long id) {

		Optional<PedidoProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:" + id + ", Tipo: " + PedidoProduto.class.getName()));
	}

	public PedidoProduto insert(PedidoProduto obj) {
		obj.setId(0);
		return repo.save(obj);
	}

	public PedidoProduto update(PedidoProduto obj) {
//		OrdemServico newObj = find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Long id) {
		PedidoProduto produto = find(id);
		try {
			repo.delete(produto);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posível excluir um pedido");
		}
	}

	public List<PedidoProduto> findAll() {
		return repo.findAll();
	}

	public Page<PedidoProduto> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	

	public PedidoProduto fromDTO(PedidoProdutoDTO objDto) {
//		PedidoProduto obj = new PedidoProduto(objDto.getId(), 
//				objDto.getProduto(),
//				objDto.getQuantidade(),
//				objDto.getPedido());
		return null;
	}

}

