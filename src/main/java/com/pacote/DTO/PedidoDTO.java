
package com.pacote.DTO;

import java.io.Serializable;
import java.util.Date;

import com.pacote.domain.Cliente;
import com.pacote.domain.Pedido;
import com.pacote.domain.enums.StatusEntrega;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;	
	private Double valorTotal;		
	private Date dataCadastro;
	private StatusEntrega statusEntrega;
	
	private Cliente cliente;
	

	public PedidoDTO(Pedido obj) {
		super();
		this.id = obj.getId();
		this.valorTotal = obj.getValorTotal();
		this.dataCadastro = obj.getDataCadastro();
		this.statusEntrega = obj.getStatusEntrega();
		this.cliente = obj.getCliente();
	}

	public PedidoDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public StatusEntrega getStatusEntrega() {
		return statusEntrega;
	}

	public void setStatusEntrega(StatusEntrega statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

}
