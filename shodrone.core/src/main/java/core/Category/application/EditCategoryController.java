package core.Category.application;
import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Category.repositories.CategoryRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Description;
import eapli.framework.application.UseCaseController;

import java.util.Date;
import java.util.Optional;


/**
 * Controller for editing categories in the system.
 * This class is responsible for handling the logic of editing existing categories.
 */
@UseCaseController
public class EditCategoryController {

    /**
     * Repository for categories.
     */
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    /**
     * Edit an existing category in the system.
     * @param oldCategoryName name of the category
     * @param newCategoryName new name of the category
     * @param description new description of the category
     * @return true if the category was edited successfully, false otherwise
     */
    public boolean editCategory(CategoryName oldCategoryName, CategoryName newCategoryName, Description description) {
        if (oldCategoryName == null || oldCategoryName.toString().isEmpty()) {
            throw new IllegalArgumentException("Current category name cannot be null or empty");
        }
        if (newCategoryName == null || newCategoryName.toString().isEmpty()) {
            throw new IllegalArgumentException("New category name cannot be null or empty");
        }

        // Retrieve the existing category
        Optional<Category> optionalCategory = categoryRepository.findByName(oldCategoryName);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("Category does not exist");
        }

        Category category = (Category) optionalCategory.get();

        // Update the name, description, and lastUpdateDate
        category.name = newCategoryName;
        category.description = description;
        category.lastUpdateDate = new Date();

        // Save the updated category
        categoryRepository.save(category);
        return true;
    }
}
