package core.Category.application;
import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Category.repositories.CategoryRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Description;
import eapli.framework.application.UseCaseController;

import java.util.Date;
import java.util.List;
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
     *
     * @param oldCategoryName name of the category
     * @param newCategoryName new name of the category
     * @param description     new description of the category
     */
    public void editCategory(CategoryName oldCategoryName, CategoryName newCategoryName, Description description) {
        // Retrieve the existing category
        Optional<Category> optionalCategory = categoryRepository.findByName(oldCategoryName);
        Category category = optionalCategory.get();

        // Update the name, description, and lastUpdateDate
        category.name = newCategoryName;
        category.description = description;
        category.lastUpdateDate = new Date();

        // Save the updated category
        categoryRepository.save(category);
    }

    /**
     * List all categories in the system.
     * @return a list of categories
     */
    public List<Category> listAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public boolean categoryExists(CategoryName newName) {
        // Check if a category with the same name already exists
        return categoryRepository.findByName(newName).isPresent();
    }
}
