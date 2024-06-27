package pl.wglazer.ecommerce.sales.offering;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferCalculatorTest {
    @Test
    void zeroOfferForEmptyItems() {
        OfferCalculator calculator = new OfferCalculator();

        Offer offer = calculator.calculateOffer(Collections.emptyList());

        assertThat(offer.getFinalPrice()).isEqualTo(BigDecimal.ZERO);
    }

}
