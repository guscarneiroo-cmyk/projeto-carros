package com.example.projetoteste.api;

import com.example.projetoteste.exception.ExternalBadRequestException;
import com.example.projetoteste.exception.ExternalMappingException;
import com.example.projetoteste.exception.ExternalUnavailableException;
import com.example.projetoteste.exception.InvalidVinException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidVinException.class)
    public ResponseEntity<ErrorResponse> handleInvalidVin(InvalidVinException ex, HttpServletRequest req) {
        return build(req, HttpStatus.BAD_REQUEST, "INVALID_VIN", ex.getMessage(), null);
    }

    // 4xx externo -> 502
    @ExceptionHandler(ExternalBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleExternalBadRequest(ExternalBadRequestException ex, HttpServletRequest req) {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("provider", ex.getProvider());
        details.put("externalStatus", ex.getExternalStatus().value());
        details.put("externalBody", safeBody(ex.getExternalBody()));
        return build(req, HttpStatus.BAD_GATEWAY, "EXTERNAL_BAD_REQUEST", ex.getMessage(), details);
    }

    // 5xx/timeout externo -> 503
    @ExceptionHandler(ExternalUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleExternalUnavailable(ExternalUnavailableException ex, HttpServletRequest req) {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("provider", ex.getProvider());
        return build(req, HttpStatus.SERVICE_UNAVAILABLE, "EXTERNAL_UNAVAILABLE", ex.getMessage(), details);
    }

    // parse inesperado -> 502
    @ExceptionHandler(ExternalMappingException.class)
    public ResponseEntity<ErrorResponse> handleExternalMapping(ExternalMappingException ex, HttpServletRequest req) {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("provider", ex.getProvider());
        return build(req, HttpStatus.BAD_GATEWAY, "EXTERNAL_MAPPING", ex.getMessage(), details);
    }

    // fallback (garante DoD: sem stacktrace)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest req) {
        return build(req, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR",
                "Erro inesperado ao processar a requisição.", null);
    }

    private ResponseEntity<ErrorResponse> build(
            HttpServletRequest req,
            HttpStatus status,
            String errorCode,
            String message,
            Map<String, Object> details
    ) {
        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                status.value(),
                errorCode,
                message,
                req.getRequestURI(),
                details
        );
        return ResponseEntity.status(status).body(body);
    }

    private String safeBody(String body) {
        if (body == null) return null;
        // evita respostas gigantescas de provedor
        int max = 800;
        return body.length() > max ? body.substring(0, max) + "..." : body;
    }
}