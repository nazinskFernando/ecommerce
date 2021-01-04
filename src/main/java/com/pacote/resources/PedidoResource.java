package com.pacote.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pacote.DTO.PedidoDTO;
import com.pacote.DTO.PedidoDTOResposta;
import com.pacote.domain.Pedido;
import com.pacote.services.domain.PedidoService;

@RestController
@RequestMapping(value = "/ecommerce/v1/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Long id) {
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}	
	
	
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<Void> insert (@Valid @RequestBody PedidoDTO objDto){
		
		Pedido obj = service.fromDTO(objDto);		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PedidoDTO objDto, @PathVariable Long id){
		Pedido obj = service.fromDTO(objDto);
		obj.setId(id);
		obj= service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/*@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<PedidoDTO>> findAll() {		
		List<Pedido> list = service.findAll();
		List<PedidoDTO> listDto = list.stream().map(obj -> new PedidoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}*/
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<PedidoDTOResposta>> findAll(
			@RequestParam(value="status-entrega")String status) {		
		List<Pedido> list = service.findStatus(status);
		
		List<PedidoDTOResposta> listDto = list.stream().map(obj -> new PedidoDTOResposta(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	

	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<PedidoDTO>> findPage(
			@RequestParam(value="page", defaultValue = "0")Integer page, 
			@RequestParam(value="linesPage", defaultValue = "24")Integer linesPage, 
			@RequestParam(value="orderBy", defaultValue = "id")String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC")String direction) {	
		
		Page<Pedido> list = service.findPage(page, linesPage, orderBy, direction);
		Page<PedidoDTO> listDto = list.map(obj -> new PedidoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}