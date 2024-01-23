package io.temporal.boston.processorder.backend.messaging;

public class ShipResponse {
    private String trackingNumber;

    public ShipResponse() {
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
