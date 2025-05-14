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
     */
    public void changeCategoryStatus(CategoryName categoryName, CategoryStatus status) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.changeStatus(status);

        categoryRepository.save(category);
    }

    /**
     * List all categories in the system.
     *
     * @return a list of existing categories
     */
    public List<Category> listAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }
}
