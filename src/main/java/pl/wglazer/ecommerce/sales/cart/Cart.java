package pl.wglazer.ecommerce.sales.cart;

import java.util.ArrayList;
import pl.wglazer.ecommerce.catalog.Product;

public class Cart {
    public static Cart empty() {
        return new Cart();
    }
    public ArrayList<CartItem> listCartItems;

    public Cart() {
        listCartItems = new ArrayList<>();
    }

    public void add(Product product) {
        if (!isInCart(product)) {
            putIntoCart(product);
        } else {
            increaseProductQuantity(product);
        }
    }

    public boolean isEmpty() {
        return listCartItems.isEmpty();
    }

    public int getItemsCount() {
        return listCartItems.size();
    }

    public ArrayList<CartItem> getCartItems() {
        return this.listCartItems;
    }

    private void putIntoCart(Product product) {
        listCartItems.add(new CartItem(product.getId(), 1, product.getPrice()));
    }

    private void increaseProductQuantity(Product product) {
        var existingCartItem = listCartItems.stream().filter(x -> x.getProductId() == product.getId()).findFirst().orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
        } 

    }

    private boolean isInCart(Product product) {
        return listCartItems.stream().filter(x -> x.getProductId() == product.getId()).findFirst().orElse(null) != null;
    }
}
