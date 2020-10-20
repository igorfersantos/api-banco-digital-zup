package br.com.igorfersantos.bancodigitalzup.exception;

public class FileStorageException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String exception) {
		super(exception);
	}
	
	public FileStorageException(String exception, Throwable cause) {
		super(exception, cause);
	}
	
}
