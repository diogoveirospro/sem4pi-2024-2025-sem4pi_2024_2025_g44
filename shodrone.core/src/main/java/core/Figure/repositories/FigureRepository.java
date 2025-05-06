package core.Figure.repositories;

import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.FigureID;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface FigureRepository extends DomainRepository<FigureID, Figure> {

    List<Figure> getPublicCatalogue();
}
