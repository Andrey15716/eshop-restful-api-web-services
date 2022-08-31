package by.teachmeskills.eshop.dto.converts;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConverter {
    private final CategoryRepository categoryRepository;

    public ProductConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductDto toDto(Product product) {
        return Optional.ofNullable(product).map(p -> ProductDto.builder()
                .id(p.getId())
                .categoryId(product.getCategory().getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .imageName(p.getImageName())
                .build()).orElse(null);
    }

    public Product fromDto(ProductDto productDto) {
        return Optional.ofNullable(productDto).map(pdto -> {
                return Product.builder()
                        .id(pdto.getId())
                        .category(categoryRepository.getCategoryById(pdto.getCategoryId()))
                        .name(pdto.getName())
                        .description(pdto.getDescription())
                        .price(pdto.getPrice())
                        .imageName(pdto.getImageName())
                        .build();
        }).orElse(null);
    }
}