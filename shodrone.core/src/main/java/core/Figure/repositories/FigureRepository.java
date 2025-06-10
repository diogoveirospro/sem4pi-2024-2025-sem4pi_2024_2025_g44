package core.Figure.repositories;

import core.Category.domain.Entities.Category;
import core.Customer.domain.Entities.Customer;
import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.FigureID;
import core.Figure.domain.ValueObjects.Keyword;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface FigureRepository extends DomainRepository<FigureID, Figure> {

    /**
     * Returns a list of all public and active figures in the catalogue.
     * @return a list of public figures
     */
    List<Figure> getPublicCatalogue();

    /**
     * Returns a list of all figures in the catalogue that are exclusive to a specific customer.
     * @param customer the customer for whom to find exclusive figures
     * @return a list of exclusive figures for the given customer
     */
    List<Figure> findExclusiveFiguresToCostumer(Customer customer);

    /**
     * Returns a list of all figures in the catalogue with the given category and/or keyword.
     * @param category the category to search for or null
     * @param keyword the keyword to search for or null
     * @return a list of figures
     */
    List<Figure> searchCatalogue(String category, String keyword);
}
