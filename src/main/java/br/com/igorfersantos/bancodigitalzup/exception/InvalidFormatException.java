package br.com.igorfersantos.bancodigitalzup.exception;

public class InvalidFormatException extends RuntimeException{
    private static final long serialVersionUID = 3262085839121128086L;

    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
