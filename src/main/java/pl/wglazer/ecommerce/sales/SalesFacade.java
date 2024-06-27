package pl.wglazer.ecommerce.sales;

import java.util.UUID;

import pl.wglazer.ecommerce.sales.cart.Cart;
import pl.wglazer.ecommerce.sales.cart.HashMapCartStorage;
import pl.wglazer.ecommerce.sales.offering.Offer;
import pl.wglazer.ecommerce.sales.offering.OfferCalculator;
import pl.wglazer.ecommerce.sales.payment.PaymentDetails;
import pl.wglazer.ecommerce.sales.payment.PaymentGateway;
import pl.wglazer.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.wglazer.ecommerce.sales.reservation.AcceptOfferRequest;
import pl.wglazer.ecommerce.sales.reservation.Reservation;
import pl.wglazer.ecommerce.sales.reservation.ReservationDetails;
import pl.wglazer.ecommerce.sales.reservation.ReservationRepository;

public class SalesFacade {
    private HashMapCartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;
    

    public SalesFacade(HashMapCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRespository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRespository;
        
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = offerCalculator.calculateOffer(cart.getCartItems());
        return offer;
    }

    public void addProduct(String customerId, String productId) {
        Cart cart = getCartForCustomer(customerId);

        cart.add(productId);

    }

    private Cart getCartForCustomer(String customerId) {
        return cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getFinalPrice())
        );

        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl());
    }
}
