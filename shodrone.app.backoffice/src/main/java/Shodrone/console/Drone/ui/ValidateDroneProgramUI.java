package Shodrone.console.Drone.ui;

import core.Drone.application.Service.DroneValidate;
import core.Drone.application.Service.DroneValidationResult;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

public class ValidateDroneProgramUI extends AbstractFancyUI {

    private final DroneValidate droneValidate = new DroneValidate();

    @Override
    protected boolean doShow() {
        try {
            // Prompt user to input the drone code
            String droneCode = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the drone code to validate: " + UtilsUI.RESET);

            // Validate the drone code using the plugin
            DroneValidationResult result = droneValidate.validateTemplate(droneCode);

            // Display the validation result
            if (result.isValid()) {
                System.out.println(UtilsUI.GREEN + "Validation successful! No errors found." + UtilsUI.RESET);
            } else {
                System.out.println(UtilsUI.RED + "Validation failed. Errors:" + UtilsUI.RESET);
                result.errors().forEach(error -> System.out.println(UtilsUI.YELLOW + "- " + error + UtilsUI.RESET));
            }

            return true;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + "An error occurred during validation: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Validate Drone Program";
    }
}