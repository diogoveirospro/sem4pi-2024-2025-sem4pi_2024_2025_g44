package Shodrone.console.Figure.actions;

import Shodrone.console.Figure.printer.FiguresPrinter;
import core.Figure.application.DecommissionFigureController;
import core.Figure.domain.Entities.Figure;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.List;

/**
 * UI for decommissioning a figure.
 * This class is responsible for displaying the list of figures
 */
public class DecommissionFigureUI extends AbstractFancyUI {

    /**
     * Controller for decommissioning figures.
     */
    DecommissionFigureController controller = new DecommissionFigureController();

    /**
     * Decommission a figure from the catalogue.
     * @return true if the user wants to continue, false otherwise
     */
    @Override
    protected boolean doShow() {
        Figure figure = selectFigure();
        if (figure == null) {
            return false;
        }

        boolean decommissioned = decommissionFigure(figure);
        if (decommissioned) {
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nFigure decommissioned successfully." + UtilsUI.RESET);
        } else {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to decommission the figure." + UtilsUI.RESET);
        }
        UtilsUI.goBackAndWait();
        return true;
    }

    /**
     * Headline for the decommission figure action.
     * @return the headline for the action
     */
    @Override
    public String headline() {
        return "Decommission Figure";
    }

    /**
     * Decommission a figure from the catalogue.
     * @param figure the figure to decommission
     * @return true if the figure was successfully decommissioned, false otherwise
     */
    public boolean decommissionFigure(Figure figure){
        return controller.decommissionFigure(figure);
    }

    /**
     * Select a figure from the catalogue.
     * @return the selected figure or null if no figure was selected
     */
    public Figure selectFigure() {
        List<Figure> figures = controller.listCatalogue();
        if (figures == null || figures.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo active figures available for decommissioning." + UtilsUI.RESET);
            return null;
        }

        FiguresPrinter printer = new FiguresPrinter();
        ListWidget<Figure> figureListWidget = new ListWidget<>("\nChoose a Figure:\n", figures, printer);
        figureListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(figures);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled." + UtilsUI.RESET);
                return null;
            }

            if (option < 0 || option >= figures.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return figures.get(option);
            }
        } while (true);
    }

}
