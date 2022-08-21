package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order create(Order entity) throws RepositoryExceptions {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public List<Order> read() throws RepositoryExceptions {
        return entityManager.createQuery("select o from Order o").getResultList();
    }

    @Override
    public Order update(Order entity) throws RepositoryExceptions {
        Order order = entityManager.find(Order.class, entity.getId());
        order.setPriceOrder(entity.getPriceOrder());
        order.setId(entity.getId());
        entityManager.persist(order);
        return order;
    }

    @Override
    public void delete(int id) throws RepositoryExceptions {
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
    }
}