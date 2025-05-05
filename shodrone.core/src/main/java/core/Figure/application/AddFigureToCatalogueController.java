package core.Figure.application;

import core.Category.domain.Entities.Category;
import core.Category.repositories.CategoryRepository;
import core.Figure.domain.Entities.Figure;
import core.Figure.repositories.FigureRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class AddFigureToCatalogueController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final FigureRepository figureRepository = PersistenceContext.repositories().figures();
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();

    public void addFigureToCatalogue(Figure figure){
        Preconditions.noneNull(figure);
        authz.ensureAuthenticatedUserHasAnyOf(Role.valueOf("SHOWDESIGNER"));
        figureRepository.save(figure);
    }

    public Iterable<Category> listCategories() {
        return categoryRepository.findAll();
    }
}
