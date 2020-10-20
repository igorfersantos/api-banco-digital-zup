package br.com.igorfersantos.bancodigitalzup.exception;

public class DuplicateException extends RuntimeException{
    private static final long serialVersionUID = 3262085839121128086L;

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
