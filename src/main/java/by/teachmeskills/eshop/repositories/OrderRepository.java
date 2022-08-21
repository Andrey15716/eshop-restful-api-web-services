package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;

public interface OrderRepository extends BaseRepository<Order> {
    Order create(Order order) throws RepositoryExceptions;
}