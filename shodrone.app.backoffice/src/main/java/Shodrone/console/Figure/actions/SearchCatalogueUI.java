package Shodrone.console.Figure.actions;

import core.Figure.application.SearchCatalogueController;
import core.Figure.domain.Entities.Figure;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.visitor.Visitor;
import shodrone.UtilsUI;

import java.util.List;

/**
 * UI for searching figures in the catalogue.
 * This class is responsible for displaying the search results
 */
public class SearchCatalogueUI extends AbstractUI {

    /**
     * Controller for searching figures in the catalogue.
     */
    SearchCatalogueController controller = new SearchCatalogueController();

    /**
     * List all figures in the catalogue with the given category and/or keyword.
     * @return true if the user wants to continue, false otherwise
     */
    @Override
    protected boolean doShow() {
        String category = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the category (or leave blank for " +
                "all categories): " + UtilsUI.RESET);
        String keyword = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the keyword (or leave blank for all " +
                "keywords): " + UtilsUI.RESET);

        if (category != null && category.isBlank()) {
            category = null;
        }

        if (keyword != null && keyword.isBlank()) {
            keyword = null;
        }

        showSearchCatalogue(category, keyword);
        return true;
    }

    /**
     * Show the search results in the catalogue.
     * @param category the category to search for or null
     * @param keyword the keyword to search for or null
     */
    private void showSearchCatalogue(String category, String keyword){
        List<Figure> figures = controller.listSearchResults(category, keyword);

        if (figures.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo figures found with the given category and/or keyword."
                    + UtilsUI.RESET);
            return;
        }

        for (Figure figure : figures) {
            if (figure.isExclusive()){
                System.out.printf("Code: %s | Version: %s | Description: %s | Exclusivity: %s%n",
                        figure.identity().code(), figure.identity().version(), figure.description(), figure.exclusivity().customer().name());
            } else {
                System.out.printf("Code: %s | Version: %s | Description: %s | Exclusivity: %s%n",
                        figure.identity().code(), figure.identity().version(), figure.description(), "None");
            }
        }

    }

    /**
     * Returns the name of the action.
     * @return the name of the action
     */
    @Override
    public String headline() {
        return "Search Figures in the Catalogue";
    }
}
