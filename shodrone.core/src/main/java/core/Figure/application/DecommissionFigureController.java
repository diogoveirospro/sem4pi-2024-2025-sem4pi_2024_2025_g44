package core.Figure.application;

import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.Code;
import core.Figure.domain.ValueObjects.FigureID;
import core.Figure.domain.ValueObjects.Version;
import core.Figure.repositories.FigureRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the decommission figure use case.
 * This class is responsible for handling the decommissioning of figures.
 */
@UseCaseController
public class DecommissionFigureController {

    /**
     * Repository for accessing figure data.
     */
    FigureRepository repository = PersistenceContext.repositories().figures();

    /**
     * Decommissions a figure.
     * @param figure the figure to decommission.
     * @return true if the figure was successfully decommissioned, false otherwise.
     */
    public boolean decommissionFigure(Figure figure) {
        figure.decommission();
        repository.save(figure);
        return true;
    }

    /**
     * Lists all active figures in the catalogue.
     * @return a list of figures in the catalogue.
     */
    public List<Figure> listCatalogue() {
        Iterable<Figure> allFigures = repository.findAll();
        List<Figure> activeFigures = new ArrayList<>();

        for (Figure figure : allFigures) {
            if (figure.isActive()) {
                activeFigures.add(figure);
            }
        }

        return activeFigures;
    }
}
