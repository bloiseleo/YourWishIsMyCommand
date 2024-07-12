package br.com.yourwishismycommand.application.dtos;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class APIBaseResponse {
    private Instant time;
    private int status;
    private String message;
    public APIBaseResponse(
            int status,
            String message
    ) {
        time = Instant.now();
        this.status = status;
        this.message = message;
    }
    public APIBaseResponse(
            Instant time,
            int status,
            String message
    ) {
        this.time = time;
        this.status = status;
        this.message =message;
    }
    public APIBaseResponse(
            Instant time,
            HttpStatus status,
            String message
    ) {
        this.time = time;
        this.status = status.value();
        this.message =message;
    }
    public Instant getTime() {
        return time;
    }
    public String getMessage() {
        return message;
    }
    public int getStatus() {
        return status;
    }
}
