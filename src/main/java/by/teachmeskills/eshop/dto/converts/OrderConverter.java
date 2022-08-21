package by.teachmeskills.eshop.dto.converts;

import by.teachmeskills.eshop.dto.OrderDto;
import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    private final ProductConverter productConverter;
    private final UserRepository userRepository;

    public OrderConverter(ProductConverter productConverter, UserRepository userRepository) {
        this.productConverter = productConverter;
        this.userRepository = userRepository;
    }

    public OrderDto toDto(Order order) {
        return Optional.ofNullable(order).map(o -> OrderDto.builder()
                .id(o.getId())
                .date(o.getDate())
                .priceOrder(o.getPriceOrder())
                .userId(o.getUser().getId())
                .productList(Optional.ofNullable(o.getProductList())
                        .map(products -> products.stream()
                                .map(productConverter::toDto)
                                .collect(Collectors.toList()))
                        .orElse(List.of()))
                .build()).orElse(null);
    }

    public Order fromDto(OrderDto orderDto) {
        return Optional.ofNullable(orderDto).map(odto -> Order.builder()
                .id(odto.getId())
                .date(odto.getDate())
                .priceOrder(odto.getPriceOrder())
                .user(userRepository.getUserId(odto.getId()))
                .productList(Optional.ofNullable(odto.getProductList())
                        .map(products -> products.stream()
                                .map(productConverter::fromDto)
                                .collect(Collectors.toList()))
                        .orElse(List.of()))
                .build()).orElse(null);
    }
}