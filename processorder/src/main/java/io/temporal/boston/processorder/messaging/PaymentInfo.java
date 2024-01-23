package io.temporal.boston.processorder.messaging;

public class PaymentInfo {
    private String accountId;
    private String authorizedFundsToken;

    public PaymentInfo() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAuthorizedFundsToken() {
        return authorizedFundsToken;
    }

    public void setAuthorizedFundsToken(String authorizedFundsToken) {
        this.authorizedFundsToken = authorizedFundsToken;
    }
}
