package io.temporal.boston.processorder.backend.messaging;

public class ChargeRequest {
    private  String customerId;
    private  String orderId;
    private  PaymentInfo paymentInfo;

    public ChargeRequest(String customerId, String orderId, PaymentInfo paymentInfo) {

        this.customerId = customerId;
        this.orderId = orderId;
        this.paymentInfo = paymentInfo;
    }

    public ChargeRequest() {
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

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
