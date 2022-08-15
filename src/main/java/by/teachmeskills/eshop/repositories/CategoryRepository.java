package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Category;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Category> {
    Category getCategoryById(int id);

    List<Category> getAllCategories();
}