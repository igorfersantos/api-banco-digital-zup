package br.com.igorfersantos.bancodigitalzup.exception;

public class AgeException extends RuntimeException {

    private static final long serialVersionUID = 6813292360287835895L;

    public AgeException(String message) {
        super(message);
    }

    public AgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
