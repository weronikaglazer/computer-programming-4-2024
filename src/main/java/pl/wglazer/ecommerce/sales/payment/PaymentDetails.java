package pl.wglazer.ecommerce.sales.payment;

public class PaymentDetails {
    private final String paymentUrl;
    private final String transactionId;

    public PaymentDetails(String paymentUrl, String transactionId) {
        this.paymentUrl = paymentUrl;
        this.transactionId = transactionId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
