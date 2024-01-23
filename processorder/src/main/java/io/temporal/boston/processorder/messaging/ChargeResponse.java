package io.temporal.boston.processorder.messaging;

public class ChargeResponse {
    private String token;

    public ChargeResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }
}
