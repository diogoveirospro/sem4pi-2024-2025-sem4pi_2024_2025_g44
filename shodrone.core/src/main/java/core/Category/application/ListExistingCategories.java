package core.Category.application;
import core.Category.domain.Entities.Category;
import core.Category.repositories.CategoryRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class ListExistingCategories {

    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    /**
     * List all existing categories.
     *
     * @return a list of existing categories
     */
    public Iterable<Category> listExistingCategories() {
        return categoryRepository.getCategories();
    }
}
