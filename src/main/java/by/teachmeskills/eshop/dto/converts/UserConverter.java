package by.teachmeskills.eshop.dto.converts;

import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    private final OrderConverter orderConverter;
    private final RoleConverter roleConverter;

    public UserConverter(OrderConverter orderConverter, RoleConverter roleConverter) {
        this.orderConverter = orderConverter;
        this.roleConverter = roleConverter;
    }

    public UserDto toDto(User user) {
        return Optional.ofNullable(user).map(u -> UserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .dateBorn(u.getDateBorn())
                .surname(u.getSurname())
                .password(u.getPassword())
                .role(u.getRole().getName())
                .orders(Optional.ofNullable(u.getOrders())
                        .map(products -> products.stream()
                                .map(orderConverter::toDto)
                                .collect(Collectors.toList()))
                        .orElse(List.of()))
                .build()).orElse(null);
    }

    public User fromDto(UserDto userDto) {
        return Optional.ofNullable(userDto).map(udto -> User.builder()
                .id(udto.getId())
                .name(udto.getName())
                .surname(udto.getSurname())
                .dateBorn(udto.getDateBorn())
                .password(udto.getPassword())
                .orders(Optional.ofNullable(udto.getOrders())
                        .map(products -> products.stream()
                                .map(orderConverter::fromDto)
                                .collect(Collectors.toList()))
                        .orElse(List.of()))
                .build()).orElse(null);
    }
}