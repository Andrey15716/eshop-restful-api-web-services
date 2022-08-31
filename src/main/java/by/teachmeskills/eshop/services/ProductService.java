package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends BaseServices<Product> {
    List<ProductDto> getProductsBySearchRequest(String param) throws ServiceExceptions, RepositoryExceptions;

    ProductDto getProductById(int id) throws ServiceExceptions, RepositoryExceptions;

    Page<Product> getAllProductsByCategoryId(int categoryId, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions;

    ProductDto createProduct(ProductDto productDto) throws RepositoryExceptions;

    ProductDto updateProduct(ProductDto productDto) throws RepositoryExceptions;

    void deleteProduct(int id) throws RepositoryExceptions;

}