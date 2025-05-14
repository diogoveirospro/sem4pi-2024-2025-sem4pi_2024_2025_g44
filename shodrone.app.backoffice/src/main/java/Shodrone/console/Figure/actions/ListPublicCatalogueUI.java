package Shodrone.console.Figure.actions;

import core.Figure.application.ListPublicCatalogueController;
import core.Figure.domain.Entities.Figure;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

/**
 * UI for listing all public figures in the catalogue.
 * This class is responsible for displaying the list of public figures
 */
public class ListPublicCatalogueUI extends AbstractFancyListUI<Figure> {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Controller for listing public figures in the catalogue.
     */
    ListPublicCatalogueController controller = new ListPublicCatalogueController();

    /**
     * Method to show the UI.
     * @return true if the UI was shown successfully
     */
    @Override
    public boolean doShow() {
        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)){
            final Iterable<Figure> figures = elements();
            if (!figures.iterator().hasNext()) {
                System.out.println(emptyMessage());
                UtilsUI.goBackAndWait();
                return true;
            }

            System.out.println(listHeader());
            for (Figure figure : figures) {
                elementPrinter().visit(figure);
            }

            UtilsUI.goBackAndWait();
            return true;
        }

        return false;
    }

    /**
     * List all public figures in the catalogue.
     * @return Iterable of figures
     */
    @Override
    protected Iterable<Figure> elements() {
        return controller.listPublicCatalogue();
    }

    /**
     * Prints the details of each figure in the catalogue.
     * @return Visitor that prints the details of each figure
     */
    @Override
    protected Visitor<Figure> elementPrinter() {
        return figure -> System.out.printf(
                "%-15s | %-10s | %-50s\n",
                figure.identity().code().toString(),
                figure.identity().version().toString(),
                figure.description().toString()
        );
    }

    /**
     * Returns the name of the element to be printed.
     * @return the name of the element
     */
    @Override
    protected String elementName() {
        return "";
    }

    /**
     * Returns the header for the list of figures.
     * @return the header for the list
     */
    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("%-15s | %-10s | %-50s", "CODE", "VERSION", "DESCRIPTION") + "\n"
                + String.format("%-15s-+-%-10s-+-%-50s", "-".repeat(15), "-".repeat(10), "-".repeat(50))
                + UtilsUI.RESET;
    }


    /**
     * Returns the message to be displayed when there are no figures in the catalogue.
     * @return the message to be displayed
     */
    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "No Active Public Figures Found!!" + UtilsUI.RESET;
    }

    /**
     * Returns the title of the UI.
     * @return the title of the UI
     */
    @Override
    public String headline() {
        return "List Public Catalogue";
    }
}
