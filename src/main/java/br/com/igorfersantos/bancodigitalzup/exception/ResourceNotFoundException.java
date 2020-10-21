package br.com.igorfersantos.bancodigitalzup.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private HttpStatus status = null;
	
	public ResourceNotFoundException(String exception) {
		super(exception);
	}

	public ResourceNotFoundException(String exception, HttpStatus status){
		super(exception);
		this.status = status;
	}
	
}