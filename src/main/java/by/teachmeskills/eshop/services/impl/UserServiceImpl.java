package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.dto.converts.CategoryConverter;
import by.teachmeskills.eshop.dto.converts.UserConverter;
import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.AuthorizationsExceptions;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.repositories.UserRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final UserConverter userConverter;
    private final CategoryConverter categoryConverter;

    public UserServiceImpl(UserRepository userRepository, CategoryService categoryService, UserConverter userConverter, CategoryConverter categoryConverter) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.userConverter = userConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public User create(User entity) throws ServiceExceptions, RepositoryExceptions {
        return userRepository.addUser(entity);
    }

    @Override
    public List<User> read() throws ServiceExceptions, RepositoryExceptions {
        return userRepository.read();
    }

    @Override
    public User update(User entity) throws ServiceExceptions, RepositoryExceptions {
        return userRepository.update(entity);
    }

    @Override
    public void delete(int id) throws ServiceExceptions, RepositoryExceptions {
        userRepository.delete(id);
    }

    @Override
    public List<CategoryDto> authenticate(UserDto userDto) throws ServiceExceptions, RepositoryExceptions, AuthorizationsExceptions {
        if (Optional.ofNullable(userDto).isPresent()
                && Optional.ofNullable(userDto.getName()).isPresent()
                && Optional.ofNullable(userDto.getPassword()).isPresent()) {
            User user = userConverter.fromDto(userDto);
            User loggedUser = userRepository.getUserByLoginAndPass(user.getName(), user.getPassword());
            if (Optional.ofNullable(loggedUser).isPresent()) {
                user.setId(loggedUser.getId());
                List<Category> categoriesList = categoryService.read();
                log.info("User is authenticated!");
                return categoriesList.stream().map(categoryConverter::toDto).collect(Collectors.toList());
            } else {
                log.info("User is not found!");
                throw new AuthorizationsExceptions("User is not authorised!");
            }
        }
        return null;
    }

    @Override
    public UserDto addNewUser(UserDto userDto) throws ServiceExceptions, RepositoryExceptions {
        User user = userConverter.fromDto(userDto);
        User userAdded = create(user);
        log.info("New user has been added!");
        return userConverter.toDto(userAdded);
    }
}