package io.temporal.boston.processorder.messaging;

public class PaymentInfo {
    public String accountId;

    public PaymentInfo() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
