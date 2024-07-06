package pl.wglazer.ecommerce.sales.offering;

import java.math.BigDecimal;

public class Offer {
    private BigDecimal finalPrice;
    private int itemsCount;

    public Offer(BigDecimal finalPrice, int itemsCount) {
        this.finalPrice = finalPrice;
        this.itemsCount = itemsCount;
    }

    public BigDecimal getFinalPrice() {
        return this.finalPrice;
    }

    public Integer getItemsCount() {
        return this.itemsCount;
    }
}
