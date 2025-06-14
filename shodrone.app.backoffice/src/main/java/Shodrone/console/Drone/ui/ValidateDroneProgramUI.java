package Shodrone.console.Drone.ui;

import core.Drone.application.Service.DroneValidate;
import core.Drone.application.Service.DroneValidationResult;
import org.antlr.v4.runtime.CharStream;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.File;
import java.util.Scanner;

public class ValidateDroneProgramUI extends AbstractFancyUI {

    private final DroneValidate droneValidate = new DroneValidate();

    @Override
    protected boolean doShow() {
        try {
            System.out.print("Please enter the path to the drone code file for validation: ");
            Scanner scanner = new Scanner(System.in);
            String filePath = scanner.nextLine();

// Agora sim, ler o ficheiro
            String fileContent = UtilsUI.lerFicheiroComoString(filePath);
            // Validate the drone code using the plugin
            DroneValidationResult result = droneValidate.validateTemplate(fileContent);

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