package Shodrone.console.Drone;

import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.AddDroneController;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import eapli.framework.presentation.console.AbstractUI;
import shodrone.presentation.UtilsUI;

import java.util.regex.Pattern;

public class AddDroneUI extends AbstractUI {

    private final AddDroneController controller = new AddDroneController();

    @Override
    protected boolean doShow() {
        try {
            SerialNumber serialNumber = enterValidSerialNumber();
            ModelName modelName = enterValidModelName();

            boolean modelExists = controller.verifyModel(modelName);
            if (!modelExists) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Model not found. Operation cancelled." + UtilsUI.RESET);
                return false;
            }

            System.out.println(UtilsUI.YELLOW + "\nConfirm adding drone with:");
            System.out.println("Serial Number: " + serialNumber);
            System.out.println("Model Name: " + modelName);
            if (!UtilsUI.confirm("Do you want to proceed? (y/n)")) {
                throw new UserCancelledException(UtilsUI.YELLOW + "Operation cancelled by user." + UtilsUI.RESET);
            }

            boolean success = controller.addDrone(serialNumber, modelName);
            if (success) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Drone added successfully!" + UtilsUI.RESET);
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Drone already exists or could not be added." + UtilsUI.RESET);
            }

            UtilsUI.goBackAndWait();
            return success;

        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + "Invalid input: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + "Unexpected error: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
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

    private ModelName enterValidModelName() {
        String input;
        String nameRegex = "^[A-Za-z0-9\\- ]+$";
        do {
            try {
                input = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter Model Name: " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(input)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + "Action cancelled by user." + UtilsUI.RESET);
                }
                if (!Pattern.matches(nameRegex, input)) {
                    throw new IllegalArgumentException("Model name contains invalid characters.");
                }
                return new ModelName(input);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    @Override
    public String headline() {
        return "Add Drone to Inventory";
    }
}
