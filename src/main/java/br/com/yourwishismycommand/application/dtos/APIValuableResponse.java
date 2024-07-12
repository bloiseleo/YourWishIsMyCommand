package br.com.yourwishismycommand.application.dtos;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class APIValuableResponse<T> extends APIBaseResponse {
    private T payload;
    public APIValuableResponse(Instant time, int status, String message, T payload) {
        super(time, status, message);
        this.payload = payload;
    }
    public APIValuableResponse(Instant time, HttpStatus status, String message, T payload) {
        super(time, status, message);
        this.payload = payload;
    }
    public T getPayload() {
        return payload;
    }
    public void setPayload(T payload) {
        this.payload = payload;
    }
}
