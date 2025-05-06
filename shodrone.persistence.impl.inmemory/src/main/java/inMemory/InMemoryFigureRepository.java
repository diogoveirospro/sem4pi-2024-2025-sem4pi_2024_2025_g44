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
}
