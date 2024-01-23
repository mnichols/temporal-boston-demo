package io.temporal.boston.processorder.messaging;

public class ShipRequest {
    private  String shipmentToken;

    public ShipRequest(String shipmentResponseToken) {

        this.shipmentToken = shipmentResponseToken;
    }

    public ShipRequest() {
    }

    public String getShipmentToken() {
        return shipmentToken;
    }

    public void setShipmentToken(String shipmentToken) {
        this.shipmentToken = shipmentToken;
    }
}
