package com.pacote.DTO;

import java.io.Serializable;

public class RespostaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	private Boolean sucesso;
	private String descricao;
	public RespostaDTO(String mensagem, Boolean sucesso, String descricao) {
		super();
		this.mensagem = mensagem;
		this.sucesso = sucesso;
		this.descricao = descricao;
	}
	public RespostaDTO() {
		
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Boolean getSucesso() {
		return sucesso;
	}
	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
