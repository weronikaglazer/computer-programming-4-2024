package pl.wglazer.ecommerce.sales.cart;

import java.util.HashMap;
import java.util.Optional;

public class HashMapCartStorage {
    HashMap<String, Cart> carts;

    public HashMapCartStorage() {
        this.carts = new HashMap<>();
    }

    public Optional<Cart> getForCustomer(String customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }

    public void save(String customerId, Cart cart) {
        carts.put(customerId, cart);

    }
}
