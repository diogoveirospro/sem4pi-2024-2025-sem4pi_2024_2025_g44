package Shodrone.console.Drone;

import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.AddDroneController;
import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddDroneUI extends AbstractFancyUI {

    private final AddDroneController controller = new AddDroneController();

    @Override
    protected boolean doShow() {
        SerialNumber serialNumber = enterValidSerialNumber();

        Model selectedModel = selectModel();

        if (selectedModel == null){
            return false;
        }
        if (controller.addDrone(serialNumber, selectedModel)){
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n Drone added successfully!" + UtilsUI.RESET);
            return true;
        }else {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n Drone already exists!" + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return "List Active Drones by Model";
    }

    private Model selectModel() {
        Iterable<Model> models = controller.listModels();
        if (models == null || !models.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No models available." + UtilsUI.RESET);
            return null;
        }

        List<Model> modelList = new ArrayList<>();
        models.forEach(modelList::add);

        ListWidget<Model> modelListWidget = new ListWidget<>("Choose a Model", modelList, new ModelPrinter());
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
        String input;
        String serialRegex = "^[A-Z0-9-]{3,30}$"; // adjust as needed
        do {
            try {
                input = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter Serial Number: " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(input)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + "Action cancelled by user." + UtilsUI.RESET);
                }
                if (!Pattern.matches(serialRegex, input)) {
                    throw new IllegalArgumentException("Serial number format invalid.");
                }
                return new SerialNumber(input);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }


}

