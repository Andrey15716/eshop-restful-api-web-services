package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;

public interface OrderService extends BaseServices<Order> {
    Order create(Order order) throws ServiceExceptions;
}