package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;

import java.util.List;

public interface ProductService extends BaseServices<Product> {
    List<ProductDto> getProductsBySearchRequest(String param) throws ServiceExceptions, RepositoryExceptions;

    ProductDto getProductById(int id) throws ServiceExceptions, RepositoryExceptions;

    List<Product> getAllProductsByCategoryIdPagination(int categoryId, int pageNumber) throws ServiceExceptions, RepositoryExceptions;

    ProductDto createProduct(ProductDto productDto) throws RepositoryExceptions;

    ProductDto updateProduct(ProductDto productDto) throws RepositoryExceptions;

    void deleteProduct(int id) throws RepositoryExceptions;

}