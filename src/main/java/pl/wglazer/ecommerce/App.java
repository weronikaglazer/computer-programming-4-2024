package pl.wglazer.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import pl.wglazer.ecommerce.catalog.ArrayListProductStorage;
import pl.wglazer.ecommerce.catalog.ProductCatalog;
import pl.wglazer.ecommerce.payu.PayU;
import pl.wglazer.ecommerce.payu.PayUCredentials;
import pl.wglazer.ecommerce.payu.PayUMapper;
import pl.wglazer.ecommerce.sales.SalesFacade;
import pl.wglazer.ecommerce.sales.cart.HashMapCartStorage;
import pl.wglazer.ecommerce.sales.offering.OfferCalculator;
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
        var pid1 = catalog.addProduct("Monstera", "nice plant");
        catalog.changePrice(pid1, BigDecimal.valueOf(59.99));

        var pid2 = catalog.addProduct("Calathea", "another nice plant");
        catalog.changePrice(pid2, BigDecimal.valueOf(67.99));

        return catalog;
    }

    @Bean
    SalesFacade createSales() {
        return new SalesFacade(
                new HashMapCartStorage(),
                new OfferCalculator(),
                new PayU(new RestTemplate(), PayUCredentials.sandbox(
                    "300746",
                    "2ee86a66e5d97e3fadc400c9f19b065d")),
                createCatalog(),
                new PayUMapper()
        );
    }
}
