package com.pdv.pdv_backend.config.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    private final HttpStatus httpStatus;

    private ApiException(HttpStatus status, String mensaje){
        super(mensaje);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus(){return httpStatus;}

    public static ApiException noEncontrado(String m){return new ApiException(HttpStatus.NOT_FOUND, m);}
    public static ApiException conflicto(String m){return new ApiException(HttpStatus.CONFLICT, m);}
    public static ApiException invalido(String m){return new ApiException(HttpStatus.BAD_REQUEST, m);}
    public static ApiException noAutorizado(String m){return new ApiException(HttpStatus.FORBIDDEN, m);}
}
