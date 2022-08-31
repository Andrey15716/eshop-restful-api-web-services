package by.teachmeskills.eshop.dto.converts;

import by.teachmeskills.eshop.dto.CartDto;
import by.teachmeskills.eshop.entities.Cart;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartConverter {
    private final ProductConverter productConverter;

    public CartConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public CartDto toDto(Cart cart) {
        return Optional.ofNullable(cart).map(c -> CartDto.builder()
                        .userId(c.getId())
                        .totalPrice(c.getTotalPrice() * c.getQuantity())
                        .products(c.getProductsCart().entrySet()
                                .stream()
                                .collect(Collectors.toMap(Map.Entry::getKey,
                                        e -> productConverter.toDto(e.getValue()))))
                        .build())
                .orElse(null);
    }

    public Cart fromDto(CartDto cartDto) {
        return Optional.ofNullable(cartDto).map(cdto -> Cart.builder()
                        .id(cdto.getUserId())
                        .totalPrice(cdto.getTotalPrice() * cdto.getQuantity())
                        .products(cdto.getProducts().entrySet()
                                .stream()
                                .collect(Collectors.toMap(Map.Entry::getKey,
                                        e -> productConverter.fromDto(e.getValue()))))
                        .build())
                .orElse(null);
    }
}