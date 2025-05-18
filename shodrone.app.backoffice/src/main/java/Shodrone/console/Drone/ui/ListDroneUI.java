package Shodrone.console.Drone.ui;

import Shodrone.console.Drone.printer.DronePrinter;
import Shodrone.console.Model.printer.ModelPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.ListDroneController;
import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Model;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * UI class responsible for listing all active drones of a selected model.
 * Inherits from {@link AbstractFancyListUI} to take advantage of generic list-handling behavior.
 */
public class ListDroneUI extends AbstractFancyListUI<Drone> {

    private final ListDroneController controller = new ListDroneController();
    private Model selectedModel;

    /**
     * Executes the logic to select a model and display all corresponding active drones.
     *
     * @return true if the process completed successfully, false if canceled or empty
     */
    @Override
    protected boolean doShow() {
        try {
            // Prompt user to select a model before listing
            selectedModel = selectModel();
            if (selectedModel == null) return false;

            final Iterable<Drone> drones = elements();
            if (!drones.iterator().hasNext()) {
                System.out.println(emptyMessage());
                UtilsUI.goBackAndWait();
                return true;
            }

            System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nActive Drones of Model: " + selectedModel.identity() + "\n" + UtilsUI.RESET);

            System.out.println(listHeader());
            for (Drone drone : drones) {
                elementPrinter().visit(drone);
            }

            UtilsUI.goBackAndWait();
            return true;

        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Returns the title/header shown at the top of the UI screen.
     *
     * @return A string representing the headline
     */
    @Override
    public String headline() {
        return "List Active Drones by Model";
    }

    /**
     * Retrieves the list of drones for the selected model.
     *
     * @return Iterable containing drones of the selected model
     */
    @Override
    protected Iterable<Drone> elements() {
        if (selectedModel == null) {
            return new ArrayList<>();
        }
        return controller.getDrnModelList(selectedModel);
    }

    /**
     * Returns a {@link Visitor} responsible for printing individual drone elements.
     *
     * @return DronePrinter instance
     */
    @Override
    protected Visitor<Drone> elementPrinter() {
        return new DronePrinter();
    }

    /**
     * Returns the name of the entity being listed.
     *
     * @return String representing the entity name
     */
    @Override
    protected String elementName() {
        return "Drone";
    }

    /**
     * Builds and returns the formatted list header for console output.
     *
     * @return String containing the list header
     */
    @Override
    protected String listHeader() {
        return UtilsUI.BOLD + String.format("%-20s | %-20s | %-10s |",
                "SERIAL NUMBER", "MODEL", "STATUS") + "\n"
                + String.format("%-20s-+-%-20s-+-%-10s-+", "-".repeat(20), "-".repeat(20), "-".repeat(10))
                + UtilsUI.RESET;
    }

    /**
     * Returns the message shown when no drone data is found.
     *
     * @return Empty state message string
     */
    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "\nNo drones found for the selected model." + UtilsUI.RESET;
    }

    /**
     * Prompts the user to choose a model from a list of available drone models.
     *
     * @return The selected {@link Model} or null if the list is empty
     * @throws UserCancelledException If the user explicitly cancels the operation
     */
    private Model selectModel() throws UserCancelledException {
        Iterable<Model> models = controller.listModels();

        if (models == null || !models.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No models available." + UtilsUI.RESET);
            return null;
        }

        List<Model> modelList = new ArrayList<>();
        models.forEach(modelList::add);

        ListWidget<Model> modelListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                "\nChoose a Drone Model:\n" + UtilsUI.RESET, modelList, new ModelPrinter());
        modelListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(modelList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1 || option >= modelList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return modelList.get(option);
            }
        } while (true);
    }
}
