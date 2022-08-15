package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.BaseEntity;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;

import java.util.List;

public interface BaseServices<T extends BaseEntity> {
    T create(T entity) throws ServiceExceptions, RepositoryExceptions;

    List<T> read() throws ServiceExceptions, RepositoryExceptions;

    T update(T entity) throws ServiceExceptions, RepositoryExceptions;

    void delete(int id) throws ServiceExceptions, RepositoryExceptions;
}