package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;

public interface UserRepository extends BaseRepository<User> {
    User getUserByLoginAndPass(String login, String password) throws RepositoryExceptions;

    User addUser(User user) throws RepositoryExceptions;

    User getUserId(int id);
}