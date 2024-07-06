package pl.wglazer.ecommerce.sales.offering;

import java.math.BigDecimal;
import java.util.List;

import pl.wglazer.ecommerce.sales.cart.CartItem;

public class OfferCalculator {
    public Offer calculateOffer(List<CartItem> items) {
        // every 5th for free
        // >100PLN -10PLN
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem item : items) {
            
            BigDecimal price = item.getPrice();
            Integer qty = item.getQuantity();

            int freeItems = qty / 5;
            qty = qty - freeItems;

            totalAmount = totalAmount.add(price.multiply(new BigDecimal(qty)));

            if (totalAmount.compareTo(new BigDecimal(100)) == 1) {
                totalAmount = totalAmount.subtract(new BigDecimal(10));
            }
        }

        return new Offer(totalAmount, items.size());
    }
}
