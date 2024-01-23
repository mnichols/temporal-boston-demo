package io.temporal.boston.processorder.backend.messaging;

public class PrepareShipmentRequest {
    private  String customerId;
    private  String orderId;

    public PrepareShipmentRequest(String customerId, String orderId) {

        this.customerId = customerId;
        this.orderId = orderId;
    }

    public PrepareShipmentRequest() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
