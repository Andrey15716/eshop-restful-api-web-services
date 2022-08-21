package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.BaseEntity;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    T create(T entity) throws RepositoryExceptions;

    List<T> read() throws RepositoryExceptions;

    T update(T entity) throws RepositoryExceptions;

    void delete(int id) throws RepositoryExceptions;
}