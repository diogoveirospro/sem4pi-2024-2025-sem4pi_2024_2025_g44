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
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.validations.Preconditions;

import java.util.Set;

@UseCaseController
public class AddFigureToCatalogueController {
    private final FigureRepository figureRepository = PersistenceContext.repositories().figures();
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public boolean addPublicFigureToCatalogue(Code code, Version version, Description description,
                                              DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                              ShowDesigner showDesigner){
        Preconditions.noneNull(code, version, description, dslDescription, keywords, categories, showDesigner);
        Figure figure = new Figure(code, version, description, dslDescription, keywords, categories, showDesigner);
        figureRepository.save(figure);
        return true;
    }

    public boolean addExclusiveFigureToCatalogue(Code code, Version version, Description description,
                                                 DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                                 ShowDesigner showDesigner, Exclusivity exclusivity){
        Preconditions.noneNull(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
        Figure figure = new Figure(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
        figureRepository.save(figure);
        return true;
    }

    public Iterable<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAll();
    }
}
