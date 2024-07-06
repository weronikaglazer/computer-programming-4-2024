package pl.wglazer.ecommerce.sales.cart;

import java.math.BigDecimal;

public class CartItem {
    private String productId;
    private Integer qty;
    private BigDecimal price;

    public CartItem(String productId, Integer qty, BigDecimal price) {
        this.productId = productId;
        this.qty = qty;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return qty;
    }

    public void setQuantity(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
