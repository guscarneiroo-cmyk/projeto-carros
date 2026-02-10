package com.example.projetoteste.exception;

import org.springframework.http.HttpStatus;

public class ExternalBadRequestException extends RuntimeException {

    private final String provider;
    private final HttpStatus externalStatus;
    private final String externalBody;

    public ExternalBadRequestException(String provider, HttpStatus externalStatus, String externalBody) {
        super("Falha ao consumir provedor externo: " + provider + " (status=" + externalStatus + ")");
        this.provider = provider;
        this.externalStatus = externalStatus;
        this.externalBody = externalBody;
    }

    public String getProvider() { return provider; }
    public HttpStatus getExternalStatus() { return externalStatus; }
    public String getExternalBody() { return externalBody; }
}