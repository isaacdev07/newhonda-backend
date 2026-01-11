package com.example.demo.exceptions;

//exceção para quando o usuario mandar dados invalidos
public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String message) {
		super(message);
	}

}
