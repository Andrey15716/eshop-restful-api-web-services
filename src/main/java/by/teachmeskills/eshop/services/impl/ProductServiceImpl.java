package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.converts.ProductConverter;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Product create(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> read() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public void delete(int id) throws RepositoryExceptions {
        productRepository.deleteProductById(id);
    }

    @Override
    public List<ProductDto> getProductsBySearchRequest(String param) throws RepositoryExceptions {
        List<Product> productListResult = productRepository.findAllByNameContaining(param);
        log.info("User got product by search request - " + param);
        return productListResult.stream().map(productConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int id) throws RepositoryExceptions {
        return productConverter.toDto(productRepository.getProductById(id));
    }

    @Override
    public Page<Product> getAllProductsByCategoryId(int categoryId, int pageNumber, int pageSize) throws RepositoryExceptions {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productConverter.fromDto(productDto);
        product = productRepository.save(product);
        return productConverter.toDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productConverter.fromDto(productDto);
        product = productRepository.save(product);
        return productConverter.toDto(product);
    }

    @Override
    public void deleteProduct(int id) throws RepositoryExceptions {
        productRepository.deleteProductById(id);
    }
}