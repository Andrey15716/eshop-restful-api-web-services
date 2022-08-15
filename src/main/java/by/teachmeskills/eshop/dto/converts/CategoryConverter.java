package by.teachmeskills.eshop.dto.converts;

import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.entities.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    private final ProductConverter productConverter;

    public CategoryConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public CategoryDto toDto(Category category) {
        return Optional.ofNullable(category).map(c -> CategoryDto.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .imageName(c.getImageName())
                        .productList(Optional.ofNullable(c.getProductList())
                                .map(products -> products.stream()
                                        .map(productConverter::toDto)
                                        .collect(Collectors.toList()))
                                .orElse(List.of()))
                        .build())
                .orElse(null);
    }

    public Category fromDto(CategoryDto categoryDto) {
        return Optional.ofNullable(categoryDto).map(cdto -> Category.builder()
                .id(cdto.getId())
                .name(cdto.getName())
                .imageName(cdto.getImageName())
                .build()).orElse(null);
    }
}