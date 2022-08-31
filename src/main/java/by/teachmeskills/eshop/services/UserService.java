package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends BaseServices<User> {
    UserDto addNewUser(UserDto userDto) throws ServiceExceptions, RepositoryExceptions;

    Optional<User> findByName(String name) throws RepositoryExceptions;

    UserDto getUserData(int userId, int pageNumber, int pageSize) throws RepositoryExceptions;

    User getUserByNameAndPassword(String name, String password) throws RepositoryExceptions;
}