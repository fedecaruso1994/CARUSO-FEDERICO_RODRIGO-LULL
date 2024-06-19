package com.backend.clinica_odontologica.exceptios;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
