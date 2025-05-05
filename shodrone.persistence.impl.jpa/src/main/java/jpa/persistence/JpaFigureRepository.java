package jpa.persistence;

import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.FigureID;
import core.Figure.repositories.FigureRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaFigureRepository extends JpaAutoTxRepository<Figure, Long, FigureID> implements FigureRepository {

    public JpaFigureRepository(final TransactionalContext autoTx) {
        super(autoTx, "figureID");
    }

    public JpaFigureRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "figureID");
    }

}
