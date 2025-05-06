package Shodrone.console.Figure.printer;

import core.Figure.application.ListPublicCatalogueController;
import core.Figure.domain.Entities.Figure;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import shodrone.UtilsUI;

/**
 * UI for listing all public figures in the catalogue.
 * This class is responsible for displaying the list of public figures
 */
public class ListPublicCatalogueUI extends AbstractListUI {

    /**
     * Controller for listing public figures in the catalogue.
     */
    ListPublicCatalogueController controller = new ListPublicCatalogueController();

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
        return figure -> System.out.printf(figure.toString());
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
        return "All Active Public Figures";
    }

    /**
     * Returns the message to be displayed when there are no figures in the catalogue.
     * @return the message to be displayed
     */
    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "\nNo Active Public Figures Found!!" + UtilsUI.RESET;
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
