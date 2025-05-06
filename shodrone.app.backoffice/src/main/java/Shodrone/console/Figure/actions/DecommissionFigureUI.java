package Shodrone.console.Figure.actions;

import core.Figure.application.DecommissionFigureController;
import core.Figure.domain.Entities.Figure;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import shodrone.UtilsUI;

import java.util.List;

/**
 * UI for decommissioning a figure.
 * This class is responsible for displaying the list of figures
 */
public class DecommissionFigureUI extends AbstractUI {

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
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Figure decommissioned successfully." + UtilsUI.RESET);
        } else {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Failed to decommission the figure." + UtilsUI.RESET);
        }
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
    public Figure selectFigure(){
        List<Figure> figures = controller.listCatalogue();

        if (figures.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No active figures available for decommissioning." + UtilsUI.RESET);
            return null;
        }

        ListWidget<Figure> figureListWidget = new ListWidget<>("Choose a Figure", figures, Figure::toString);
        figureListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(figures);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
                return null;
            }

            if (option < 1 || option > figures.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                Figure selected = figures.get(option - 1);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Selected Figure: " + selected + UtilsUI.RESET);
                return selected;
            }

        } while (true);

    }
}
