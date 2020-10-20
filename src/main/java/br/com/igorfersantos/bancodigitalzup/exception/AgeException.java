package br.com.igorfersantos.bancodigitalzup.exception;

public class AgeException extends RuntimeException{
    private static final long serialVersionUID = 3262085839121128086L;

    public AgeException(String message) {
        super(message);
    }

    public AgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
