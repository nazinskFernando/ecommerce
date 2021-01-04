package com.pacote.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();

	

	public ValidationError(Long timestamp, Integer status, String error, String mensagem, String path) {
		super(timestamp, status, error, mensagem, path);
		
	}

	public List<FieldMessage> getErrors() {
		return erros;
	}

	public void addError(String fieldName, String mensagem) {
		erros.add(new FieldMessage(fieldName, mensagem));
	}
	

}
