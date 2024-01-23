package io.temporal.boston.processorder.backend.messaging;

public class CheckFraudRequest {
    private  String customerId;
    private  PaymentInfo paymentInfo;
    private  String productId;

    public CheckFraudRequest(String customerId, PaymentInfo paymentInfo, String productId) {

        this.customerId = customerId;
        this.paymentInfo = paymentInfo;
        this.productId = productId;
    }

    public CheckFraudRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
