package by.teachmeskills.eshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class Cart extends BaseEntity {
    private Map<Integer, Product> products;
    private int totalPrice;
    private int quantity;

    public Cart() {
        this.products = new HashMap<>();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        totalPrice += product.getPrice();
    }

    public void deleteProduct(int productId) {
        Product product = products.get(productId);
        products.remove(productId);
        totalPrice -= product.getPrice();
    }

    public void clearCart() {
        products.clear();
    }

    public Cart(int totalPrice) {
        this.totalPrice = totalPrice * quantity;
    }

    public Map<Integer, Product> getProductsCart() {
        return products;
    }
}