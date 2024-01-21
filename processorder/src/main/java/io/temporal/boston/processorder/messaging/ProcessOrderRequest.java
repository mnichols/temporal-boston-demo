package io.temporal.boston.processorder.messaging;

public class ProcessOrderRequest {
    public ProcessOrderRequest() {
    }

    public String orderId;

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public PaymentInfo paymentInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
