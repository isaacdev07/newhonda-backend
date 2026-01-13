package com.example.demo.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

public class ResourceExceptionHandler {

	//tratamento do erro 
	public ResponseEntity<StandardError> businessException(BusinessException e, HttpServletRequest request){
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Bad Request / Invalid Parameter");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		//retorna o json preenchido com as informações do erro
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value()); 
        err.setError("Validation Exception");
        err.setMessage("Erro na validação dos campos");
        err.setPath(request.getRequestURI());

        // pega cada campo que deu erro e adiciona na lista
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        //retorna o json preenchido com os erros
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
	//tratando o erro 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
