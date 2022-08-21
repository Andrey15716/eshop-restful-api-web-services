package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.converts.CategoryConverter;
import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final CategoryConverter categoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Category create(Category entity) throws ServiceExceptions, RepositoryExceptions {
        return categoryRepository.create(entity);
    }

    @Override
    public List<Category> read() throws ServiceExceptions, RepositoryExceptions {
        return categoryRepository.read();
    }

    @Override
    public Category update(Category entity) throws ServiceExceptions, RepositoryExceptions {
        return categoryRepository.update(entity);
    }

    @Override
    public void delete(int id) throws ServiceExceptions, RepositoryExceptions {
        categoryRepository.delete(id);
    }

    @Override
    public CategoryDto getCategoryDataPagination(int id, int pageNumber) throws ServiceExceptions, RepositoryExceptions {
        Category category = categoryRepository.getCategoryById(id);
        if (Optional.ofNullable(category).isPresent()) {
            List<Product> products = productService.getAllProductsByCategoryIdPagination(category.getId(), pageNumber);
            category.setProductList(products);
        }
        return categoryConverter.toDto(category);
    }

    @Override
    public List<CategoryDto> getHomePageInformation() {
        return categoryRepository.getAllCategories().stream().map(categoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws RepositoryExceptions {
        Category category = categoryConverter.fromDto(categoryDto);
        category = categoryRepository.create(category);
        return categoryConverter.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) throws RepositoryExceptions {
        Category category = categoryConverter.fromDto(categoryDto);
        category = categoryRepository.update(category);
        return categoryConverter.toDto(category);
    }

    @Override
    public void deleteCategory(int id) throws RepositoryExceptions {
        categoryRepository.delete(id);
    }
}