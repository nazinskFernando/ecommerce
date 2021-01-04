package com.pacote.services.exceptions;

public class AccessDeniedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AccessDeniedException(String msg) {
		super(msg);
	}
	public AccessDeniedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
