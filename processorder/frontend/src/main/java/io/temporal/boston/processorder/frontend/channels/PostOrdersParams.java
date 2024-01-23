package io.temporal.boston.processorder.frontend.channels;

public class PostOrdersParams {
    private int count;
    private String productId;

    public PostOrdersParams() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
