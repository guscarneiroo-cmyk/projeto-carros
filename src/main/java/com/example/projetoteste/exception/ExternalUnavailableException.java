package com.example.projetoteste.exception;

public class ExternalUnavailableException extends RuntimeException {

    private final String provider;

    public ExternalUnavailableException(String provider, String message, Throwable cause) {
        super(message, cause);
        this.provider = provider;
    }

    public ExternalUnavailableException(String provider, String message) {
        super(message);
        this.provider = provider;
    }

    public String getProvider() { return provider; }
}