package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {
    List<Product> getProductsBySearchRequest(String param) throws RepositoryExceptions;

    Product getProductById(int id) throws RepositoryExceptions;

    List<Product> getAllProductsByCategoryIdPaging(int categoryId, int pageReq);
}