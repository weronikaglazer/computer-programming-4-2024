package pl.wglazer.ecommerce.sales.payment;
import java.math.BigDecimal;
import pl.wglazer.ecommerce.sales.reservation.AcceptOfferRequest;
import pl.wglazer.ecommerce.sales.reservation.CustomerDetails;
import pl.wglazer.ecommerce.sales.reservation.Reservation;

public class RegisterPaymentRequest {
    String email;
    String fname;
    String lname;
    String reservationId;
    BigDecimal total;


    public RegisterPaymentRequest(String email, String fname, String lname, String reservationId, BigDecimal total) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.reservationId = reservationId;
        this.total = total;
    }

    public static RegisterPaymentRequest of(String reservationId, AcceptOfferRequest acceptOfferRequest, BigDecimal total) {
        return new RegisterPaymentRequest(
            acceptOfferRequest.getEmail(),
            acceptOfferRequest.getFname(),
            acceptOfferRequest.getLname(),
            reservationId,
            total
        );
    }
}
