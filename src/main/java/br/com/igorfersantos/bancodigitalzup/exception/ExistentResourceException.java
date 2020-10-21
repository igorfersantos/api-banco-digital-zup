package br.com.igorfersantos.bancodigitalzup.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Getter
public class ExistentResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpHeaders httpHeaders = null;

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ExistentResourceException(String exception) {
        super(exception);
    }

    public ExistentResourceException(String exception, HttpStatus status) {
        super(exception);
        this.status = status;
    }

    public ExistentResourceException(String exception, HttpHeaders httpHeaders, HttpStatus status) {
        super(exception);
        this.httpHeaders = httpHeaders;
        this.status = status;
    }

}