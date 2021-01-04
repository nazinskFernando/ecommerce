package com.pacote.services.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pacote.DTO.ClienteDTO;
import com.pacote.DTO.PedidoDTOCadastrar;
import com.pacote.DTO.PedidoProdutoDTO;
import com.pacote.DTO.RespostaDTO;
import com.pacote.domain.Cliente;
import com.pacote.domain.Pedido;
import com.pacote.domain.PedidoProduto;
import com.pacote.domain.enums.StatusCliente;
import com.pacote.repositories.ClienteRepository;
import com.pacote.repositories.PedidoRepository;
import com.pacote.services.exceptions.DataIntegrityException;
import com.pacote.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoProdutoService pedidoProdutoService;

	public Cliente find(long id) {

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! id:" + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Pedido> findPedidos(long id) {
		Optional<Cliente> cliente = repo.findById(id);

		List<Pedido> list = pedidoRepo.findByCliente(cliente.get());
		return list;
	}

	public RespostaDTO insertPedido(PedidoDTOCadastrar objDto) {
		try {
			Pedido obj = new Pedido();
			obj.setDataCadastro(objDto.getDataCadastro());
			obj.setValorTotal(objDto.getValorTotal());
			obj.setStatusEntrega(objDto.getStatusEntrega());
			obj.setId(0);
			obj.setCliente(find(objDto.getIdCliente()));
			obj = pedidoService.insert(obj);

			for (PedidoProdutoDTO pedidoProdutoDTO : objDto.getProdutos()) {
				PedidoProduto pedidoProduto = new PedidoProduto();
				pedidoProduto.setProduto(produtoService.find(pedidoProdutoDTO.getProduto().getId()));
				pedidoProduto.setPedido(obj);
				pedidoProduto.setQuantidade(pedidoProdutoDTO.getQuantidade());
				pedidoProdutoService.insert(pedidoProduto);
			}
			
			RespostaDTO resposta = new RespostaDTO();
			resposta.setSucesso(true);
			resposta.setMensagem("Pedido criado com sucesso");
			resposta.setDescricao("Id Pedido " + obj.getId());
			
			return resposta;
		} catch (Exception e) {
			RespostaDTO resposta = new RespostaDTO();
			resposta.setSucesso(false);
			resposta.setMensagem("Erro ao criar pedido: " + e.getMessage());
			resposta.setDescricao("");
			return resposta;
		}
	

	}

	public RespostaDTO insert(Cliente obj) {
		
		try {
			obj.setId(0);

			repo.save(obj);
			RespostaDTO resposta = new RespostaDTO();
			resposta.setSucesso(true);
			resposta.setMensagem("Cliente criado com sucesso");
			resposta.setDescricao("Id Cliente " + obj.getId());
			
			return resposta;
		} catch (Exception e) {
			RespostaDTO resposta = new RespostaDTO();
			resposta.setSucesso(false);
			resposta.setMensagem("Erro ao criar pedido: " + e.getMessage());
			resposta.setDescricao("");
			return resposta;
		}
		
		
	}

	public RespostaDTO update(Cliente obj) {
		try {

			repo.save(obj);
			RespostaDTO resposta = new RespostaDTO();
			resposta.setSucesso(true);
			resposta.setMensagem("Cliente criado com sucesso");
			resposta.setDescricao("Id Cliente " + obj.getId());
			
			return resposta;
		} catch (Exception e) {
			RespostaDTO resposta = new RespostaDTO();
			resposta.setSucesso(false);
			resposta.setMensagem("Erro ao criar pedido: " + e.getMessage());
			resposta.setDescricao("");
			return resposta;
		}
	}

	public void delete(long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posível excluir uma categoria que possui produtos");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	/*
	 * public Page<Cliente> findPage(Integer page, Integer linesPage, String
	 * orderBy, String direction) { PageRequest pageRequest = PageRequest.of(page,
	 * linesPage, Direction.valueOf(direction), orderBy); return
	 * repo.findAll(pageRequest); }
	 */

	public List<Cliente> findPage(String status, Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		StatusCliente statusCliente = null;
		switch (status.toLowerCase()) {
		case "ativo":
			statusCliente = StatusCliente.ATIVO;
			break;
		case "inativo":
			statusCliente = StatusCliente.INATIVO;
			break;
		}
		return repo.findByStatusCliente(statusCliente, pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getDataCadastro(), objDto.getStatusCliente());
	}

}
