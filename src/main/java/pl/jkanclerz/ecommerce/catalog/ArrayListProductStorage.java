package pl.jkanclerz.ecommerce.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListProductStorage {
    private ArrayList<Product> products;

    public ArrayListProductStorage() {
        this.products = new ArrayList<>();
    }

    public List<Product> allProducts() {
        return Collections.unmodifiableList(products);
    }

    public void add(Product product) {
        products.add(product);
    }

    public Product getProductBy(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get();
    }

}
