package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.LOGIN;
import static by.teachmeskills.eshop.utils.EshopConstants.PASSWORD;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(User entity) throws RepositoryExceptions {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public List<User> read() throws RepositoryExceptions {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User update(User entity) throws RepositoryExceptions {
        User user = entityManager.find(User.class, entity.getId());
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setPassword(entity.getPassword());
        user.setDateBorn(entity.getDateBorn());
        entityManager.persist(user);
        return user;
    }

    @Override
    public void delete(int id) throws RepositoryExceptions {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByLoginAndPass(String login, String password) {
        Query query = entityManager.createQuery("select u from User u where u.name=:login and u.password=:password");
        query.setParameter(LOGIN, login);
        query.setParameter(PASSWORD, password);
        return (User) query.getSingleResult();
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getUserId(int id) {
        return entityManager.find(User.class, id);
    }
}