package com.pacote.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String mensagem;
	
	
	public FieldMessage() {
	}
	
	
	public FieldMessage(String fieldName, String mensagem) {
		super();
		this.fieldName = fieldName;
		this.mensagem = mensagem;
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	

}
