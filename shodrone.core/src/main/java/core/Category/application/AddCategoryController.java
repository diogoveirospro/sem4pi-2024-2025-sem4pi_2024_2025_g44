package core.Category.application;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Category.repositories.CategoryRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Description;
import eapli.framework.application.UseCaseController;


/**
 * Controller for adding categories to the system.
 * This class is responsible for handling the logic of adding new categories.
 */
@UseCaseController
public class AddCategoryController {
    /**
     * Repository for categories.
     */
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    /**
     * Add a new category to the system.
     * @param categoryName name of the category
     * @param description description of the category
     * @return true if the category was added successfully, false otherwise
     */
    public boolean addCategory(CategoryName categoryName, Description description) {
        if (categoryName == null || categoryName.toString().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        Category category = new Category(categoryName, description);
        // Check if the category already exists
        if (categoryRepository.findByName(categoryName).isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }
        categoryRepository.save(category);
        return true;
    }
}
