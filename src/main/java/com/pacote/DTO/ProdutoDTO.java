
package com.pacote.DTO;

import java.io.Serializable;

import com.pacote.domain.Produto;

public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;	
	private String nome;
	private Double valor;
	private Boolean disponivel;
	

	public ProdutoDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.valor = obj.getValor();
		this.disponivel = obj.getDisponivel();
	}

	public ProdutoDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	

}
