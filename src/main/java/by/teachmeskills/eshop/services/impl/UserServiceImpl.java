package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.dto.converts.CategoryConverter;
import by.teachmeskills.eshop.dto.converts.OrderConverter;
import by.teachmeskills.eshop.dto.converts.UserConverter;
import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.Role;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.repositories.OrderRepository;
import by.teachmeskills.eshop.repositories.RoleRepository;
import by.teachmeskills.eshop.repositories.UserRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.utils.EshopConstants.ROLE_USER;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final UserConverter userConverter;
    private final CategoryConverter categoryConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public UserServiceImpl(UserRepository userRepository, CategoryService categoryService, UserConverter userConverter,
                           CategoryConverter categoryConverter, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                           OrderRepository orderRepository, OrderConverter orderConverter) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.userConverter = userConverter;
        this.categoryConverter = categoryConverter;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
    }

    @Override
    public User create(User entity) throws RepositoryExceptions {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        Role role = roleRepository.findByName(ROLE_USER);
        if (Optional.ofNullable(role).isPresent()) {
            entity.setRole(role);
        }
        log.info("User has been created");
        return userRepository.save(entity);
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto addNewUser(UserDto userDto) throws RepositoryExceptions {
        User user = userConverter.fromDto(userDto);
        User userAdded = create(user);
        log.info("New user has been added!");
        return userConverter.toDto(userAdded);
    }

    @Override
    public Optional<User> findByName(String name) throws RepositoryExceptions {
        return userRepository.getUserByName(name);
    }

    @Override
    public UserDto getUserData(int userId, int pageNumber, int pageSize) {
        try {
            User loggedInUser = userRepository.getUserById(userId);
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
            Page<Order> orders = orderRepository.getOrdersByUserId(userId, pageable);
            return UserDto.builder()
                    .id(loggedInUser.getId())
                    .name(loggedInUser.getName())
                    .surname(loggedInUser.getSurname())
                    .password(loggedInUser.getPassword())
                    .dateBorn(loggedInUser.getDateBorn())
                    .orders(Optional.of(orders.getContent()).map(products -> products.stream()
                            .map(orderConverter::toDto).toList()).orElse(List.of()))
                    .role(loggedInUser.getRole().getName())
                    .build();
        } catch (Exception e) {
            log.warn("Profile data error");
            return null;
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws RepositoryExceptions {
        Optional<User> user = findByName(name);
        passwordEncoder.matches(password, user.get().getPassword());
        log.info("Password encoder has been accepted");
        return user.get();
    }
}