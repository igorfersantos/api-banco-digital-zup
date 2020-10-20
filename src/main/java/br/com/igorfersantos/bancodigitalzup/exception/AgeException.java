package br.com.igorfersantos.bancodigitalzup.exception;

import java.io.Serializable;

public class AgeException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 3262085839121128086L;

    public AgeException(String message) {
        super(message);
    }

    public AgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
