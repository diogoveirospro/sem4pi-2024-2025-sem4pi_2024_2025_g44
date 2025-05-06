package core.Figure.application;

import core.Figure.domain.Entities.Figure;
import core.Figure.repositories.FigureRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.List;

/**
 * Controller for listing public figures in the catalogue.
 */
@UseCaseController
public class ListPublicCatalogueController {

    /**
     * Repository for figures.
     */
    FigureRepository figureRepository = PersistenceContext.repositories().figures();

    /**
     * List all public figures in the catalogue.
     * Public figures are defined as those that are not exclusive and are currently active.
     *
     * @return a list of public figures
     */
    public List<Figure> listPublicCatalogue() {
        return figureRepository.getPublicCatalogue();
    }
}
