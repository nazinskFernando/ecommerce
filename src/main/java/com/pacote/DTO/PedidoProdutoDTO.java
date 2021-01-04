
package com.pacote.DTO;

import java.io.Serializable;

import com.pacote.domain.PedidoProduto;
import com.pacote.domain.Produto;

public class PedidoProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Produto produto;
	private int quantidade;	
	

	public PedidoProdutoDTO(PedidoProduto obj) {
		super();
		this.produto = obj.getProduto();
		this.quantidade = obj.getQuantidade();
	}

	public PedidoProdutoDTO() {}


	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	

}
