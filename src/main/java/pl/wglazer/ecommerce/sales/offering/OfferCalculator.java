package pl.wglazer.ecommerce.sales.offering;

import java.math.BigDecimal;
import java.util.List;

import pl.wglazer.ecommerce.sales.cart.CartItem;

public class OfferCalculator {
    public Offer calculateOffer(List<CartItem> items) {
        // every 5th for free
        // >100PLN -10PLN
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.valueOf(0);

        for (CartItem item : items) {
            
            totalAmount.add(BigDecimal.valueOf(20.5));
        
            // Calculate the number of free items
            //int freeItems = quantity / 5;

            // Calculate the cost of the items after applying the "every 5th item free" rule
            //double costForItems = (quantity - freeItems) * productPrice;
            //totalAmount += costForItems;

            // Calculate discount for every 5th item
            //totalDiscount += freeItems * productPrice;
        }

        BigDecimal finalPrice = totalAmount.subtract(totalDiscount);

        return new Offer(finalPrice);
    }
}
