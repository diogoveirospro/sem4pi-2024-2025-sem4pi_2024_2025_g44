package Shodrone.console.Drone.ui;

import Shodrone.console.Model.printer.ModelPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.AddDroneController;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class AddDroneUI extends AbstractFancyUI {

    private final AddDroneController controller = new AddDroneController();

    @Override
    protected boolean doShow() {
        try {
            SerialNumber serialNumber = enterValidSerialNumber();

            Model selectedModel = selectModel();
            if (selectedModel == null) return false;

            if (controller.addDrone(serialNumber, selectedModel)) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nDrone added successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nDrone already exists!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }

        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.BOLD + UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Add a Drone to the Inventory";
    }

    private Model selectModel() {
        Iterable<Model> models = controller.listModels();

        if (models == null || !models.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo models available." + UtilsUI.RESET);
            return null;
        }

        List<Model> modelList = new ArrayList<>();
        models.forEach(modelList::add);

        ListWidget<Model> modelListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\nChoose a Model: \n" +
                UtilsUI.RESET, modelList, new ModelPrinter());
        modelListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(modelList);

            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return modelList.get(option);
            }
        } while (true);
    }

    private SerialNumber enterValidSerialNumber() {
        String serialRegex = "^[0-9]{3,30}$";

        while (true) {
            try {
                String input = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Serial number (3-30 digits): " + UtilsUI.RESET).trim();

                if (!input.matches(serialRegex)) {
                    throw new IllegalArgumentException("\nMust be 3 to 30 digits (only numbers).");
                }

                int serial = Integer.parseInt(input);

                if (serial <= 0) {
                    throw new IllegalArgumentException("\nSerial number must be a positive integer.");
                }

                return new SerialNumber(serial);

            } catch (NumberFormatException e) {
                System.out.println(UtilsUI.RED + "\nInvalid number format." + UtilsUI.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            }
        }
    }
}
