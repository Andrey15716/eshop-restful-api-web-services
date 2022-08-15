package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.SEARCH_PARAM;

@Tag(name = "search", description = "The Search API")
@RestController
@RequestMapping("/search")
public class SearchController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public SearchController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Search product",
            description = "Search product in eshop",
            tags = {"search"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Search product was found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Product was not found - NO CONTENT"
            )
    })

    @PostMapping
    public ResponseEntity<List<ProductDto>> getSearchPage(@NotNull(message = "field must not be null or empty")
                                                          @RequestParam(SEARCH_PARAM) String searchParam) throws RepositoryExceptions, ServiceExceptions {
        if (searchParam.isBlank()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ProductDto> productDtoList = productService.getProductsBySearchRequest(searchParam);
            return new ResponseEntity<>(productDtoList, HttpStatus.FOUND);
        }
    }
}