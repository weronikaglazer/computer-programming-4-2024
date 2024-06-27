package pl.wglazer.ecommerce.sales;

import org.junit.jupiter.api.Test;

import pl.wglazer.ecommerce.sales.cart.HashMapCartStorage;
import pl.wglazer.ecommerce.sales.offering.Offer;
import pl.wglazer.ecommerce.sales.offering.OfferCalculator;
import pl.wglazer.ecommerce.sales.reservation.ReservationRepository;
import pl.wglazer.ecommerce.sales.reservation.SpyPaymentGateway;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class SalesTest {

    @Test
    void itShowsCurrentOffer() {
        String customerId = thereIsCustomer("Weronika");
        SalesFacade sales = thereIsSales();

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getFinalPrice()).isEqualTo(BigDecimal.ZERO);
        assertThat(offer.getItemsCount()).isEqualTo(0);
    }

    @Test
    void itAddsProductToCart() {
        String productId = thereIsExampleProduct("Sunglasses", BigDecimal.valueOf(10));
        String customerId = thereIsCustomer("Weronika");
        SalesFacade sales = thereIsSales();

        sales.addProduct(customerId, productId);

        Offer offer = sales.getCurrentOffer(customerId);

        assertThat(offer.getFinalPrice()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(offer.getItemsCount()).isEqualTo(1);
    }

    private String thereIsExampleProduct(String name, BigDecimal price) {
        return name;
    }

    @Test
    void itAcceptCustomersCurrentOffer() {

    }

    @Test
    void itConfirmPayment() {

    }

    private SalesFacade thereIsSales() {
        return new SalesFacade(
                new HashMapCartStorage(),
                new OfferCalculator(),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }

    private String thereIsCustomer(String name) {
        return name;
    }
}
