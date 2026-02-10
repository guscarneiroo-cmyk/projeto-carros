package com.example.projetoteste.exception;

public class ExternalMappingException extends RuntimeException {

    private final String provider;

    public ExternalMappingException(String provider, String message, Throwable cause) {
        super(message, cause);
        this.provider = provider;
    }

    public String getProvider() { return provider; }
}