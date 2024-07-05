package pl.wglazer.ecommerce.sales.reservation;

import java.math.BigDecimal;
import java.time.Instant;

import pl.wglazer.ecommerce.sales.offering.Offer;
import pl.wglazer.ecommerce.sales.payment.PaymentDetails;

public class Reservation {
    private String reservationId;
    private CustomerDetails customerDetails;
    private BigDecimal total;
    private Instant paidAt;
    private String transactionId;

    public Reservation(String reservationId, CustomerDetails customerDetails, BigDecimal total, String transactionId) {
        this.reservationId = reservationId;
        this.customerDetails = customerDetails;
        this.total = total;
        this.transactionId = transactionId;
    }

    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, Offer offer, PaymentDetails paymentDetails) {
        return new Reservation(
            reservationId,
            new CustomerDetails(customerId, acceptOfferRequest.getFname(), acceptOfferRequest.getLname(), acceptOfferRequest.getEmail()),
            offer.getFinalPrice(),
            paymentDetails.getTransactionId()
        );
    }

    public boolean isPending() {
        return paidAt == null;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getId() {
        return reservationId;
    }
}
