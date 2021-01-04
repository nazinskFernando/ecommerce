
package com.pacote.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.pacote.domain.Pedido;
import com.pacote.domain.PedidoProduto;
import com.pacote.domain.enums.StatusEntrega;

public class PedidoDTOResposta implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;	
	private Double valorTotal;		
	private Date dataCadastro;
	private StatusEntrega statusEntrega;
	
	private long idCliente;
	private Set<PedidoProdutoDTO> produtos = new HashSet<>();

	public PedidoDTOResposta(Pedido obj) {
		super();
		this.id = obj.getId();
		this.valorTotal = obj.getValorTotal();
		this.dataCadastro = obj.getDataCadastro();
		this.statusEntrega = obj.getStatusEntrega();
		this.idCliente = obj.getCliente().getId();
		
		for (PedidoProduto produto : obj.getPedidosProduto()) {
			PedidoProdutoDTO produtoAux = new PedidoProdutoDTO();
			produtoAux.setProduto(produto.getProduto());
			produtoAux.setQuantidade(produto.getQuantidade());
			produtos.add(produtoAux);
		}
	}

	public PedidoDTOResposta() {}

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


	public Set<PedidoProdutoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<PedidoProdutoDTO> produtos) {
		this.produtos = produtos;
	}


	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
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
