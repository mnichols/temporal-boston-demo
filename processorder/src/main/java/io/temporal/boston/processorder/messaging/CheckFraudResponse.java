package io.temporal.boston.processorder.messaging;

public class CheckFraudResponse {
    private boolean ok;

    public CheckFraudResponse() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
