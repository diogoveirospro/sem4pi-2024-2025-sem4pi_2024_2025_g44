package Shodrone.console.Figure.actions;

import core.Figure.application.SearchCatalogueController;
import core.Figure.domain.Entities.Figure;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

import java.util.List;

/**
 * UI for searching figures in the catalogue.
 * This class is responsible for displaying the search results.
 */
public class SearchCatalogueUI extends AbstractFancyListUI<Figure> {

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
        String category = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter category (or leave blank): " + UtilsUI.RESET);
        String keyword = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter keyword (or leave blank): " + UtilsUI.RESET);

        if (category != null && category.isBlank()) category = null;
        if (keyword != null && keyword.isBlank()) keyword = null;

        final Iterable<Figure> figures = controller.listSearchResults(category, keyword);
        if (!figures.iterator().hasNext()) {
            System.out.println(emptyMessage());
            return true;
        }

        System.out.println(listHeader());
        for (Figure figure : figures) {
            elementPrinter().visit(figure);
        }

        UtilsUI.goBackAndWait();
        return true;
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
}
