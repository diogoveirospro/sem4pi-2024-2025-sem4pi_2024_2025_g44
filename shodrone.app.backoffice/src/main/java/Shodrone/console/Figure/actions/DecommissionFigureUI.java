package Shodrone.console.Figure.actions;

import Shodrone.console.Figure.printer.FiguresPrinter;
import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryStatus;
import core.Figure.application.DecommissionFigureController;
import core.Figure.domain.Entities.Figure;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * UI for decommissioning a figure.
 * This class is responsible for displaying the list of figures
 */
public class DecommissionFigureUI extends AbstractFancyListUI<Figure> {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Controller for decommissioning figures.
     */
    DecommissionFigureController controller = new DecommissionFigureController();

    @Override
    protected Iterable<Figure> elements() {
        return controller.listCatalogue();
    }

    @Override
    protected Visitor<Figure> elementPrinter() {
        return new FiguresPrinter();
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("%-5s | %-15s | %-10s | %-50s |", "INDEX", "CODE", "VERSION", "DESCRIPTION") + "\n"
                + String.format("%-5s-+-%-15s-+-%-10s-+-%-50S-+", "-".repeat(5),"-".repeat(15), "-".repeat(10),
                "-".repeat(50))
                + UtilsUI.RESET;
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "\nNo Figures Found!!" + UtilsUI.RESET;
    }

    /**
     * Decommission a figure from the catalogue.
     * @return true if the user wants to continue, false otherwise
     */
    @Override
    protected boolean doShow() {
        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.MANAGER)){
            final Iterable<Figure> figures = elements();
            if (!figures.iterator().hasNext()) {
                System.out.println(emptyMessage());
                UtilsUI.goBackAndWait();
                return true;
            }

            List<Figure> figureList = new ArrayList<>();
            int index = 1;
            System.out.println(listHeader());
            for (Figure figure : figures) {
                System.out.printf("%-5d | ", index++);
                elementPrinter().visit(figure);
                figureList.add(figure);
            }

            int option = 0;

            do {
                option = UtilsUI.selectsIndex(figureList);

                if (option == -2) {
                    return false;
                }

                if (option < 0 || option > figureList.size()) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
                }

            } while (option < 0 || option > figureList.size());

            Figure selectedFigure = figureList.get(option);

            if (selectedFigure == null) {
                System.out.println(UtilsUI.RED + "\nInvalid category selection." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            }

            if (decommissionFigure(selectedFigure)) {
                System.out.println(UtilsUI.BOLD + UtilsUI.GREEN + "\nFigure decommissioned successfully." + UtilsUI.RESET);
            } else {
                System.out.println(UtilsUI.BOLD + UtilsUI.RED + "\nFailed to decommission figure." + UtilsUI.RESET);
            }

            UtilsUI.goBackAndWait();
            return true;
        }

        return false;
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

}
