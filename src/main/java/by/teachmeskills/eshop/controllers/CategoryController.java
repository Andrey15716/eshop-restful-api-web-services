package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@Tag(name = "category", description = "The Category API")
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Find a particular category",
            description = "Find a particular category by id",
            tags = {"category"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category was found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Category was not found - forbidden operation"
            )
    })

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> openCategoryPage(@Min(value = 1) @PathVariable int id,
                                                        @RequestParam int pageNumber,
                                                        @RequestParam int pageSize) throws RepositoryExceptions, ServiceExceptions {
        CategoryDto categoryDto = categoryService.getCategoryData(id, pageNumber, pageSize);
        if (Optional.ofNullable(categoryDto).isPresent()) {
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Operation(
            summary = "Create new category",
            description = "Create new category in eshop",
            tags = {"category"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "New category has been created",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Category has not been created - Bad Request"
            )
    })

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws RepositoryExceptions {
        CategoryDto createCategoryDto = categoryService.createCategory(categoryDto);
        if (Optional.ofNullable(categoryDto).isPresent()) {
            return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Update category",
            description = "Update an existing category",
            tags = {"category"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "New category has been updated",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Category has not been updated - Bad Request"
            )
    })

    @PutMapping("/update")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) throws RepositoryExceptions {
        CategoryDto updateCategoryDto = categoryService.updateCategory(categoryDto);
        if (Optional.ofNullable(updateCategoryDto).isPresent()) {
            return new ResponseEntity<>(updateCategoryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Delete category",
            description = "Delete an existing category",
            tags = {"category"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "New category has been deleted",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Category has not been deleted - Bad Request"
            )
    })

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable int id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}