package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;

import java.util.List;

public interface CategoryService extends BaseServices<Category> {

    CategoryDto getCategoryDataPagination(int id, int pageNumber) throws ServiceExceptions, RepositoryExceptions;

    List<CategoryDto> getHomePageInformation();

    CategoryDto createCategory(CategoryDto categoryDto) throws RepositoryExceptions;

    CategoryDto updateCategory(CategoryDto categoryDto) throws RepositoryExceptions;

    void deleteCategory(int id) throws RepositoryExceptions;
}