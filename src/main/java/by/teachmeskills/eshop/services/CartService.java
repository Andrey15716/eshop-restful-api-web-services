package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.CartDto;
import by.teachmeskills.eshop.dto.converts.CartConverter;
import by.teachmeskills.eshop.dto.converts.UserConverter;
import by.teachmeskills.eshop.entities.Cart;
import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.repositories.OrderRepository;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CartService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartConverter cartConverter;
    private final UserConverter userConverter;

    public CartService(ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository, CartConverter cartConverter, UserConverter userConverter) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartConverter = cartConverter;
        this.userConverter = userConverter;
    }

    public CartDto addProductToCart(int productId, Cart cart) throws RepositoryExceptions {
        Product product = productRepository.getProductById(productId);
        cart.addProduct(product);
        return cartConverter.toDto(cart);
    }

    public CartDto deleteProductFromCart(int productId, Cart cart) {
        cart.deleteProduct(productId);
        return cartConverter.toDto(cart);
    }

    public CartDto deleteAllProductsFromCart(Cart cart) {
        cart.clearCart();
        return cartConverter.toDto(cart);
    }

    public String orderProducts(int userId, Cart cart) {
        try {
            List<Product> products = cart.getProducts();
            int priceOrder = cart.getTotalPrice();
            LocalDate date = LocalDate.now();
            Order order = Order.builder()
                    .priceOrder(priceOrder)
                    .date(date)
                    .user(userRepository.getUserId(userId))
                    .productList(products)
                    .build();
            orderRepository.create(order);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Product was not purchased";
        }
        log.info("User purchased product with total price: " + cart.getTotalPrice());
        return "Product was purchased";
    }
}