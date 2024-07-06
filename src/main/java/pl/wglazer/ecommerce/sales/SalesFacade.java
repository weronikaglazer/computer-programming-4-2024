package pl.wglazer.ecommerce.sales;

import java.util.UUID;
import pl.wglazer.ecommerce.catalog.Product;
import pl.wglazer.ecommerce.catalog.ProductCatalog;
import pl.wglazer.ecommerce.payu.PayU;
import pl.wglazer.ecommerce.payu.PayUMapper;
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
    private PayU payu;
    private ProductCatalog productCatalog;
    private PayUMapper payumapper;
    

    public SalesFacade(HashMapCartStorage cartStorage, OfferCalculator offerCalculator, PayU payu, ProductCatalog productCatalog, PayUMapper payumapper) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.payu = payu;
        this.productCatalog = productCatalog;
        this.payumapper = payumapper;
        
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = offerCalculator.calculateOffer(cart.getCartItems());
        return offer;
    }

    public void addProduct(String customerId, String productId) {
        Cart cart = getCartForCustomer(customerId);
        Product product = productCatalog.getProductBy(productId);
        
        if (cart.isEmpty()) {
            cartStorage.save(customerId, cart);
            cart.add(product);
        } else {
            cart.add(product);
        }
    }

    private Cart getCartForCustomer(String customerId) {
        return cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        var request = payumapper.orderCreate(acceptOfferRequest, getCurrentOffer(customerId));

        var response = payu.handle(request);

        return new ReservationDetails(reservationId, response.getRedirectUri());
    }
}
