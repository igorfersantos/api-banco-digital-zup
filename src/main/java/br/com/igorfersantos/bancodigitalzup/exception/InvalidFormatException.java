package br.com.igorfersantos.bancodigitalzup.exception;

public class InvalidFormatException extends RuntimeException{

    private static final long serialVersionUID = 5321813304182724762L;

    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
