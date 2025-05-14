package core.Category.application;
import core.Category.domain.Entities.Category;
import core.Category.repositories.CategoryRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class ListExistingCategoriesController {

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    /**
     * List all existing categories.
     *
     * @return a list of existing categories
     */
    public List<Category> listExistingCategories() {
        return (List<Category>) categoryRepository.findAll();
    }
}
