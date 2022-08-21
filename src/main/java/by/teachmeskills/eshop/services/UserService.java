package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.AuthorizationsExceptions;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends BaseServices<User> {
    List<CategoryDto> authenticate(UserDto userDto) throws ServiceExceptions, RepositoryExceptions, AuthorizationsExceptions;

    UserDto addNewUser(UserDto userDto) throws ServiceExceptions, RepositoryExceptions;
}