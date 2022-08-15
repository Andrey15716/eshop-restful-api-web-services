package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.converts.ProductConverter;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public Product create(Product entity) throws ServiceExceptions, RepositoryExceptions {
        return productRepository.create(entity);
    }

    @Override
    public List<Product> read() throws ServiceExceptions, RepositoryExceptions {
        return productRepository.read();
    }

    @Override
    public Product update(Product entity) throws ServiceExceptions, RepositoryExceptions {
        return productRepository.update(entity);
    }

    @Override
    public void delete(int id) throws ServiceExceptions, RepositoryExceptions {
        productRepository.delete(id);
    }

    @Override
    public List<ProductDto> getProductsBySearchRequest(String param) throws RepositoryExceptions {
        List<Product> productListResult = productRepository.getProductsBySearchRequest(param);
        log.info("User got product by search request - " + param);
        return productListResult.stream().map(productConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int id) throws RepositoryExceptions {
        return productConverter.toDto(productRepository.getProductById(id));
    }

    @Override
    public List<Product> getAllProductsByCategoryIdPagination(int categoryId, int pageNumber) {
        return productRepository.getAllProductsByCategoryIdPaging(categoryId, pageNumber);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) throws RepositoryExceptions {
        Product product = productConverter.fromDto(productDto);
        product = productRepository.create(product);
        return productConverter.toDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) throws RepositoryExceptions {
        Product product = productConverter.fromDto(productDto);
        product = productRepository.update(product);
        return productConverter.toDto(product);
    }

    @Override
    public void deleteProduct(int id) throws RepositoryExceptions {
        productRepository.delete(id);
    }
}