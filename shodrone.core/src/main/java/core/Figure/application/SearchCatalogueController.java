package core.Figure.application;

import core.Category.domain.Entities.Category;
import core.Category.repositories.CategoryRepository;
import core.Figure.domain.Entities.Figure;
import core.Figure.repositories.FigureRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for search figures with keyword and/or category
 */
@UseCaseController
public class SearchCatalogueController {

    /**
     * Repository of figures
     */
    FigureRepository repository = PersistenceContext.repositories().figures();

    /**
     * Repository of Categories
     */
    CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    /**
     * Search figures in the catalogue with keyword and/or category
     * @param category category
     * @param keyword keyword
     * @return list of figures
     */
    public List<Figure> listSearchResults(String category, String keyword){
        return repository.searchCatalogue(category, keyword);
    }

    /**
     * List all categories in the system.
     * @return an iterable of categories
     */
    public Iterable<Category> listCategories(){
        return categoryRepository.findAllActiveCategories();
    }

}
