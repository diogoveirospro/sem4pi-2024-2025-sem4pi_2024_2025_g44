package jpa;

import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.FigureID;
import core.Figure.repositories.FigureRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA implementation of the Figure repository.
 */
public class JpaFigureRepository extends JpaAutoTxRepository<Figure, Long, FigureID> implements FigureRepository {

    /**
     * Constructor for JPA Figure Repository.
     * @param autoTx the transactional context
     */
    public JpaFigureRepository(final TransactionalContext autoTx) {
        super(autoTx, "figureID");
    }

    /**
     * Constructor for JPA Figure Repository.
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaFigureRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "figureID");
    }

    /**
     * Returns a list of all public and active figures in the catalogue.
     * Public figures are defined as those that are not exclusive and are currently active.
     *
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
