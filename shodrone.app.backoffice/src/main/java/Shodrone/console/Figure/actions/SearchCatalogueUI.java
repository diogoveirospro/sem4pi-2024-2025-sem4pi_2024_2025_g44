package Shodrone.console.Figure.actions;

import Shodrone.console.Figure.printer.CategoriesPrinter;
import Shodrone.console.Figure.printer.CustomerPrinter;
import core.Category.domain.Entities.Category;
import core.Customer.domain.Entities.Customer;
import core.Figure.application.SearchCatalogueController;
import core.Figure.domain.Entities.Figure;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * UI for searching figures in the catalogue.
 * This class is responsible for displaying the search results.
 */
public class SearchCatalogueUI extends AbstractFancyListUI<Figure> {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Controller for searching figures in the catalogue.
     */
    private final SearchCatalogueController controller = new SearchCatalogueController();

    /**
     * List figures by keyword/category and display them.
     * @return true if UI was shown successfully
     */
    @Override
    public boolean doShow() {
        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)){
            String category = showCategoriesAndSelect();
            String keyword = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter keyword (or leave blank): " + UtilsUI.RESET);

            if (category != null && category.isBlank()) category = null;
            if (keyword != null && keyword.isBlank()) keyword = null;

            try {
                final Iterable<Figure> figures = controller.listSearchResults(category, keyword);
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
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Please Try Again!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
        }

        return false;
    }

    /**
     * Fetches search results using optional filters.
     */
    @Override
    protected Iterable<Figure> elements() {
        // Handled directly in doShow
        return List.of(); // Placeholder, not used
    }

    /**
     * Prints a formatted line for each matching figure.
     */
    @Override
    protected Visitor<Figure> elementPrinter() {
        return figure -> System.out.printf(
                "%-15s | %-10s | %-50s | %s\n",
                figure.identity().code().toString(),
                figure.identity().version().toString(),
                figure.description().toString(),
                figure.isExclusive() ? UtilsUI.RED + "Exclusive" + UtilsUI.RESET : UtilsUI.GREEN + "Public" + UtilsUI.RESET
        );
    }

    /**
     * Returns the name of the printed element.
     */
    @Override
    protected String elementName() {
        return "Figure";
    }

    /**
     * Header for the search result list.
     */
    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("\n" + "%-15s | %-10s | %-50s | %-10s", "CODE", "VERSION", "DESCRIPTION", "TYPE") + "\n"
                + String.format("%-15s-+-%-10s-+-%-50s-+-%-10s", "-".repeat(15), "-".repeat(10), "-".repeat(50), "-".repeat(10))
                + UtilsUI.RESET;
    }

    /**
     * Message when no results match the filters.
     */
    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "\nNo Figures Found for Given Filters!" + UtilsUI.RESET;
    }

    /**
     * Returns the title of this UI screen.
     */
    @Override
    public String headline() {
        return "Search Figures in the Catalogue";
    }

    /**
     * Show the categories and allow the user to select one.
     * @return the selected category.
     */
    public String showCategoriesAndSelect() {
        Iterable<Category> categories = controller.listCategories();
        if (categories == null || !categories.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo categories available." + UtilsUI.RESET);
            return null;
        }

        List<Category> categoriesList = new ArrayList<>();
        categories.forEach(categoriesList::add);

        CategoriesPrinter categoriesPrinter = new CategoriesPrinter();

        ListWidget<Category> categoryListWidget = new ListWidget<>(UtilsUI.BOLD + "\nChoose a Category:\n" +
                UtilsUI.RESET, categoriesList, categoriesPrinter);
        categoryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndexWithDifferentPrompt(categoriesList, UtilsUI.BOLD + "Type your option " +
                    "(or type '0' to leave blank): " + UtilsUI.RESET);
            if (option == -2) {
                return null;
            }

            if (option < 0 || option > categoriesList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                Category selected = categoriesList.get(option);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nSelected Category: " + selected.toString() + UtilsUI.RESET);
                return selected.name().toString();
            }

        } while (true);
    }
}
