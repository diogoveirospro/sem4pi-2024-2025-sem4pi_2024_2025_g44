package Shodrone.console.Category.printer;

import core.Category.application.ListExistingCategoriesController;
import core.Category.domain.Entities.Category;
import core.Figure.domain.Entities.Figure;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.List;

/**
 * User Interface for listing all existing categories.
 */
public class ListExistingCategoriesUI extends AbstractFancyListUI<Category> {

    private final ListExistingCategoriesController controller = new ListExistingCategoriesController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected Iterable<Category> elements() {
        return controller.listExistingCategories();
    }

    @Override
    protected Visitor<Category> elementPrinter() {
        return category -> System.out.printf(
                "%-15s | %-50s | %-30s | %-30s |\n",
                category.name().toString(),
                category.description().toString(),
                category.creationDate().toString(),
                category.lastUpdateDate().toString()
        );
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("%-15s | %-50s | %-30s | %-30s |", "NAME", "DESCRIPTION",
                "CREATION DATE", "LAST UPDATE DATE") + "\n"
                + String.format("%-15s-+-%-50s-+-%-30s-+-%-30s-+", "-".repeat(15),
                "-".repeat(50), "-".repeat(30), "-".repeat(30))
                + UtilsUI.RESET;
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.BOLD + UtilsUI.RED + "No categories found." + UtilsUI.RESET;
    }

    /**
     * Show the UI for listing categories.
     *
     * @return true if the operation was successful, false otherwise.
     */
    @Override
    public boolean doShow() {
        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.SHOWDESIGNER)){
            final Iterable<Category> categories = elements();
            if (!categories.iterator().hasNext()) {
                System.out.println(emptyMessage());
                UtilsUI.goBackAndWait();
                return true;
            }

            System.out.println(listHeader());
            for (Category category : categories) {
                elementPrinter().visit(category);
            }

            UtilsUI.goBackAndWait();
            return true;
        }

        return false;
    }

    /**
     * Display the headline for the UI.
     *
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "List Existing Categories";
    }
}