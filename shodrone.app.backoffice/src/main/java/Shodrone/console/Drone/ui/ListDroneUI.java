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
 * UI class to list all active drones of a selected model.
 */
public class ListDroneUI extends AbstractFancyListUI<Drone> {

    private final ListDroneController controller = new ListDroneController();
    private Model selectedModel;

    @Override
    protected boolean doShow() {
        try {
            // Seleciona o modelo de drone antes de listar
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

    @Override
    public String headline() {
        return "List Active Drones by Model";
    }

    @Override
    protected Iterable<Drone> elements() {
        if (selectedModel == null) {
            return new ArrayList<>();
        }
        return controller.getDrnModelList(selectedModel);
    }

    @Override
    protected Visitor<Drone> elementPrinter() {
        return new DronePrinter();
    }

    @Override
    protected String elementName() {
        return "Drone";
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD + String.format("%-20s | %-20s | %-10s |",
                "SERIAL NUMBER", "MODEL", "STATUS") + "\n"
                + String.format("%-20s-+-%-20s-+-%-10s-+", "-".repeat(20), "-".repeat(20), "-".repeat(10))
                + UtilsUI.RESET;
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "\nNo drones found for the selected model." + UtilsUI.RESET;
    }

    /**
     * Permite ao utilizador escolher um modelo de drone da lista disponível.
     *
     * @return Model escolhido ou null se cancelado
     * @throws UserCancelledException se o utilizador cancelar explicitamente
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
