package inMemory;

import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.FigureID;
import core.Figure.repositories.FigureRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory implementation of the Figure repository.
 */
public class InMemoryFigureRepository extends InMemoryDomainRepository<Figure, FigureID> implements FigureRepository {

    /**
     * Constructor for InMemoryFigureRepository.
     */
    static {
            InMemoryInitializer.init();
    }

    /**
     * Returns a list of all public and active figures in the catalogue.
     * @return a list of public figures
     */
    @Override
    public List<Figure> getPublicCatalogue() {
        Iterable<Figure> allFigures = findAll();
        List<Figure> publicFigures = new ArrayList<>();

        for (Figure figure : allFigures) {
            if (!figure.isExclusive() && figure.isActive()){
                publicFigures.add(figure);
            }
        }

        return publicFigures;
    }

    /**
     * Returns a list of all figures in the catalogue with the given category and/or keyword.
     * @param category the category to search for or null
     * @param keyword the keyword to search for or null
     * @return a list of figures
     */
    @Override
    public List<Figure> searchCatalogue(String category, String keyword) {

        List<Figure> foundFigures = new ArrayList<>();
        Iterable<Figure> allFigures = findAll();

        if (category != null && keyword != null) {

            for (Figure figure : allFigures) {
                if (figure.isActive() && figure.matchesCategory(category) && figure.matchesKeyword(keyword)) {
                    foundFigures.add(figure);
                }
            }

        } else if (category != null) {
            for (Figure figure : allFigures) {
                if (figure.isActive() && figure.matchesCategory(category)) {
                    foundFigures.add(figure);
                }
            }

        } else if (keyword != null) {
            for (Figure figure : allFigures) {
                if (figure.isActive() && figure.matchesKeyword(keyword)) {
                    foundFigures.add(figure);
                }
            }
        }

        return foundFigures;
    }

    /**
     * Returns true if the figure with the given ID exists in the catalogue.
     * @param figureID the ID of the figure to check
     * @return true if the figure exists, false otherwise
     */
    @Override
    public boolean exists(FigureID figureID) {
        for (Figure figure : findAll()) {
            if (figure.identity().equals(figureID)) {
                return true;
            }
        }
        return false;
    }
}
