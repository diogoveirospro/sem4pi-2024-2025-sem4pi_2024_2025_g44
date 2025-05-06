package core.Figure.application;

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
     * Search figures in the catalogue with keyword and/or category
     * @param category category
     * @param keyword keyword
     * @return list of figures
     */
    public List<Figure> listSearchResults(String category, String keyword){
        return repository.searchCatalogue(category, keyword);
    }

}
