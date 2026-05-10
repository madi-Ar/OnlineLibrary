package main.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {
    private LocalDateTime timeCreation;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationsErrors;

    public ErrorResponse(int status, String error, String message, String path, Map<String, String> validationsErrors) {
        this.timeCreation = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.validationsErrors = validationsErrors;
    }
}
