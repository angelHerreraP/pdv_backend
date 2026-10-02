package com.pdv.pdv_backend.config.exception;

public class VendedorException extends RuntimeException {
    public VendedorException(String mensaje) {
        super(mensaje);
    }
}