package core.Figure.application;

import core.Category.domain.Entities.Category;
import core.Category.repositories.CategoryRepository;
import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Figure.domain.Entities.Exclusivity;
import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.Code;
import core.Figure.domain.ValueObjects.DSLDescription;
import core.Figure.domain.ValueObjects.Keyword;
import core.Figure.domain.ValueObjects.Version;
import core.Figure.repositories.FigureRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Description;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import eapli.framework.application.UseCaseController;
import eapli.framework.validations.Preconditions;

import java.util.Set;

/**
 * Controller for adding figures to the catalogue.
 */
@UseCaseController
public class AddFigureToCatalogueController {
    /**
     * Repository for figures.
     */
    private final FigureRepository figureRepository = PersistenceContext.repositories().figures();

    /**
     * Repository for categories.
     */
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    /**
     * Repository for customers.
     */
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    /**
     * Add a public figure to the catalogue.
     * @param code code of the figure
     * @param version version of the figure
     * @param description description of the figure
     * @param dslDescription DSL description of the figure
     * @param keywords keywords associated with the figure
     * @param categories categories associated with the figure
     * @param showDesigner show designer associated with the figure
     * @return true if the figure was added successfully, false otherwise
     */
    public boolean addPublicFigureToCatalogue(Code code, Version version, Description description,
                                              DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                              ShowDesigner showDesigner){
        Preconditions.noneNull(code, version, description, dslDescription, keywords, categories, showDesigner);
        Figure figure = new Figure(code, version, description, dslDescription, keywords, categories, showDesigner);
        figureRepository.save(figure);
        return true;
    }

    /**
     * Add an exclusive figure to the catalogue.
     * @param code code of the figure
     * @param version version of the figure
     * @param description description of the figure
     * @param dslDescription DSL description of the figure
     * @param keywords keywords associated with the figure
     * @param categories categories associated with the figure
     * @param showDesigner show designer associated with the figure
     * @param exclusivity exclusivity of the figure
     * @return true if the figure was added successfully, false otherwise
     */
    public boolean addExclusiveFigureToCatalogue(Code code, Version version, Description description,
                                                 DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                                 ShowDesigner showDesigner, Exclusivity exclusivity){
        Preconditions.noneNull(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
        Figure figure = new Figure(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
        figureRepository.save(figure);
        return true;
    }

    /**
     * List all categories in the system.
     * @return an iterable of categories
     */
    public Iterable<Category> listCategories() {
        return categoryRepository.findAll();
    }

    /**
     * List all figures in the catalogue.
     * @return an iterable of figures
     */
    public Iterable<Customer> listCustomers() {
        return customerRepository.findAll();
    }
}
