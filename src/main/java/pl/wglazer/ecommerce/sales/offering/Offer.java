package pl.wglazer.ecommerce.sales.offering;

import java.math.BigDecimal;

public class Offer {
    private BigDecimal finalPrice;

    public Offer(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getFinalPrice() {
        return this.finalPrice;
    }

    public Integer getItemsCount() {
        return 0;
    }
}
