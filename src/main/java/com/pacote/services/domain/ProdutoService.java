package com.pacote.services.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pacote.DTO.ProdutoDTO;
import com.pacote.domain.Produto;
import com.pacote.repositories.ProdutoRepository;
import com.pacote.services.exceptions.DataIntegrityException;
import com.pacote.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	

	public Produto find(long id) {

		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:" + id + ", Tipo: " + Produto.class.getName()));
	}

	public Produto insert(Produto obj) {
		obj.setId(0);
		return repo.save(obj);
	}

	public Produto update(Produto obj) {
//		OrdemServico newObj = find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Long id) {
		Produto produto = find(id);
		try {
			repo.delete(produto);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posível excluir uma categoria que possui produtos");
		}
	}

	public List<Produto> findAll() {
		return repo.findAll();
	}

	public Page<Produto> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	

	public Produto fromDTO(ProdutoDTO objDto) {
		Produto obj = new Produto(objDto.getId(), 								
							objDto.getValor(),
							objDto.getDisponivel(),
							objDto.getNome()
							);
		return obj;
	}

}

