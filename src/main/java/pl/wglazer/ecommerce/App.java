package pl.wglazer.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.wglazer.ecommerce.catalog.ArrayListProductStorage;
import pl.wglazer.ecommerce.catalog.ProductCatalog;
import pl.wglazer.ecommerce.sales.SalesFacade;
import pl.wglazer.ecommerce.sales.cart.HashMapCartStorage;
import pl.wglazer.ecommerce.sales.offering.OfferCalculator;
import pl.wglazer.ecommerce.sales.payment.PaymentDetails;
import pl.wglazer.ecommerce.sales.payment.PaymentGateway;
import pl.wglazer.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.wglazer.ecommerce.sales.reservation.ReservationRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        var pid1 = catalog.addProduct("Puppy poster 1", "nice puppy poster");
        catalog.changePrice(pid1, BigDecimal.valueOf(59.99));

        var pid2 = catalog.addProduct("Puppy poster 2", "another nice puppy poster");
        catalog.changePrice(pid2, BigDecimal.valueOf(67.99));

        return catalog;
    }

    @Bean
    SalesFacade createSales() {
        return new SalesFacade(
                new HashMapCartStorage(),
                new OfferCalculator(),
                new PaymentGateway() {
                    @Override
                    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
                        return null;
                    }
                },
                new ReservationRepository()
        );
    }
}
