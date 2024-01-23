package io.temporal.boston.processorder.backend.messaging;

public class PrepareShipmentResponse {
    private String token;

    public String getToken() {
        return this.token;
    }

    public PrepareShipmentResponse() {
    }

    public void setToken(String token) {
        this.token = token;
    }
}
