package com.pacote.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pacote.DTO.ClienteDTO;
import com.pacote.DTO.PedidoDTOCadastrar;
import com.pacote.DTO.PedidoDTOResposta;
import com.pacote.DTO.RespostaDTO;
import com.pacote.domain.Cliente;
import com.pacote.domain.Pedido;
import com.pacote.services.domain.ClienteService;

@RestController
@RequestMapping(value = "/ecommerce/v1/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable long id) {

		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}/pedidos", method = RequestMethod.GET)
	public ResponseEntity<List<PedidoDTOResposta>> findPedidos(@PathVariable long id) {

		List<Pedido> list = service.findPedidos(id);
		List<PedidoDTOResposta> listDto = list.stream().map(obj -> new PedidoDTOResposta(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

	@RequestMapping(value = "/{id}/pedidos", method = RequestMethod.POST)
	public ResponseEntity<RespostaDTO> insertPedido(@Valid @RequestBody PedidoDTOCadastrar objDto,
			@PathVariable long id) {
		objDto.setIdCliente(id);
		RespostaDTO obj = service.insertPedido(objDto);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RespostaDTO> insert(@Valid @RequestBody ClienteDTO objDto) {
				
		Cliente obj = service.fromDTO(objDto);
		RespostaDTO resposta = service.insert(obj);
		return ResponseEntity.ok().body(resposta);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RespostaDTO> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable long id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		RespostaDTO resposta = service.update(obj);
		return ResponseEntity.ok().body(resposta);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * @RequestMapping( method = RequestMethod.GET) public
	 * ResponseEntity<List<ClienteDTO>> findAll() { List<Cliente> list =
	 * service.findAll(); List<ClienteDTO> listDto = list.stream().map(obj -> new
	 * ClienteDTO(obj)).collect(Collectors.toList()); return
	 * ResponseEntity.ok().body(listDto); }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findPage(
			@RequestParam(value = "status", defaultValue = "ativo") String status,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
			@RequestParam(value = "ordem") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		List<Cliente> list = service.findPage(status, page, linesPage, orderBy, direction);
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	/*
	 * @RequestMapping(value="/page", method = RequestMethod.GET) public
	 * ResponseEntity<Page<ClienteDTO>> findPage(
	 * 
	 * @RequestParam(value="page", defaultValue = "0")Integer page,
	 * 
	 * @RequestParam(value="linesPage", defaultValue = "24")Integer linesPage,
	 * 
	 * @RequestParam(value="orderBy", defaultValue = "nome")String orderBy,
	 * 
	 * @RequestParam(value="direction", defaultValue = "ASC")String direction) {
	 * 
	 * Page<Cliente> list = service.findPage(page, linesPage, orderBy, direction);
	 * Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); return
	 * ResponseEntity.ok().body(listDto); }
	 */

}
