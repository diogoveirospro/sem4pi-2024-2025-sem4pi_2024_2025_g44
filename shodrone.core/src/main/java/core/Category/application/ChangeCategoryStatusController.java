package core.Category.application;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Category.domain.ValueObjects.CategoryStatus;
import core.Category.repositories.CategoryRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.List;

/**
 * Controller for changing the status of a category.
 * This class is responsible for handling the change of status of categories.
 */
@UseCaseController
public class ChangeCategoryStatusController {
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    /**
     * Change the status of a category.
     *
     * @param categoryName the name of the category
     * @param status       the new status of the category
     * @return true if the status was changed successfully, false otherwise
     */
    public boolean changeCategoryStatus(CategoryName categoryName, CategoryStatus status) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        // Check if the status is valid
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        // Update the status of the category
        category.changeStatus(status);
        // Save the updated category
        categoryRepository.save(category);
        // Check if the status was changed successfully
        Category updatedCategory = categoryRepository.findByName(categoryName).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        if (updatedCategory == null) {
            throw new IllegalArgumentException("Category not found");
        }
        if (!updatedCategory.status().equals(status)) {
            throw new IllegalArgumentException("Status was not changed successfully");
        }
        // Return true if the status was changed successfully
        return true;
    }

    /**
     * List all categories in the system.
     *
     * @return a list of existing categories
     */
    public List<Category> listAllCategories() {
        return categoryRepository.getCategories();
    }
}
