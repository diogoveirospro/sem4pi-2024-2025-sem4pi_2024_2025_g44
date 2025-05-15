package Shodrone.console.Model.ui;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.*;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.*;

public class CreateModelUI extends AbstractFancyUI {

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean doShow() {

        System.out.println("=== Create Drone Model ===");
        String name = UtilsUI.readLineFromConsole("Enter the model name: ");
        ModelName modelName = new ModelName(name);

        int previousLimit = 0;
        int currentLimit;

        // Input da zona segura
        do {
            currentLimit = UtilsUI.readIntegerFromConsole("Enter the max limit where the wind speed is 100% safe for the drone, no wind tolerance: ");
            if (currentLimit <= previousLimit) {
                System.out.println("The max limit must be greater than " + previousLimit + ". Try again: ");
            }
        } while (currentLimit <= previousLimit);

        WindSpeed windSpeedF = new WindSpeed(previousLimit, currentLimit);
        PositionTolerance positionToleranceF = new PositionTolerance(0.0);
        Map<WindSpeed, PositionTolerance> config = new LinkedHashMap<>();
        config.put(windSpeedF, positionToleranceF);

        previousLimit = currentLimit;

        int choice;
        double lastPosition = 0.0;

        do {
            do {
                choice = UtilsUI.readIntegerFromConsole("Press 1 to continue configuration, or 0 to stop and declare when (" + previousLimit + "m/s < windspeed) as unsafe: ");
                if (choice != 0 && choice != 1) {
                    System.out.println("Invalid choice. Please enter 1 to continue or 0 to stop: ");
                }
            } while (choice != 0 && choice != 1);

            if (choice == 0) break;

            double position;

            do {
                position = UtilsUI.readDoubleFromConsole("Enter the next wind tolerance (must be > " + lastPosition + "): ");
                if (position <= lastPosition) {
                    System.out.println("Position tolerance must be non-negative. Try again: ");
                }
            } while (position < 0);

            do {
                currentLimit = UtilsUI.readIntegerFromConsole("Enter the max limit to fly with this tolerance, " + position + ": ");
                if (currentLimit <= previousLimit) {
                    System.out.println("The max limit must be greater than " + previousLimit + ". Try again: ");
                }
            } while (currentLimit <= previousLimit);

            WindSpeed windSpeed = new WindSpeed(previousLimit, currentLimit);
            PositionTolerance positionTolerance = new PositionTolerance(position);

            config.put(windSpeed, positionTolerance);
            previousLimit = currentLimit;
            lastPosition = position;

        } while (true);


        WindSpeed windSpeed = new WindSpeed(previousLimit, 999);
        PositionTolerance positionTolerance = new PositionTolerance(-1);

        config.put(windSpeed, positionTolerance);

        // Confirm
        System.out.println("\nPlease confirm the data:");
        System.out.println("=== " + modelName + " ===");

        for (Map.Entry<WindSpeed, PositionTolerance> entry : config.entrySet()) {
            int min = entry.getKey().minSpeed();
            int max = entry.getKey().maxSpeed();
            double tolerance = entry.getValue().value();

            if (tolerance == 0.0) {
                System.out.println("wind <= " + max + " -> 0m");
            } else if (tolerance == -1.0) {
                System.out.println(min + " < wind -> Not safe to fly");
            } else {
                System.out.println(min + " < wind <= " + max + " -> " + tolerance + "m");
            }
        }

        String confirm;
        do {
            confirm = UtilsUI.readLineFromConsole("Confirm (Y/N)? \n");
            if (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N")) {
                System.out.println("Please enter Y or N.");
            }
        } while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N"));

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Operation canceled.");
            return false;
        }

        Configuration configuration = new Configuration(config, SafetyStatus.SAFE);
        boolean success = controller.createModel(modelName, configuration);

        if (success) {
            System.out.println("Model successfully created!");
        } else {
            System.out.println("Failed to create model.");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create New Drone Model";
    }
}
