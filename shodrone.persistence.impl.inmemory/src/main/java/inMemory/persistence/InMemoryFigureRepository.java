package inMemory.persistence;

import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.FigureID;
import core.Figure.repositories.FigureRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryFigureRepository extends InMemoryDomainRepository<Figure, FigureID> implements FigureRepository {

    static {
            InMemoryInitializer.init();
    }
}
