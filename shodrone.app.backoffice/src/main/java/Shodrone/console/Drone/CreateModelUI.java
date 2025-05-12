package Shodrone.console.Drone;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.*;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.*;

import static javax.swing.UIManager.put;

public class CreateModelUI extends AbstractFancyUI {

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean doShow() {

        System.out.println("=== Create Drone Model ===");
        String name = UtilsUI.readLineFromConsole("Enter the model name");
        ModelName modelName = new ModelName(name);

        int previousLimit = 0;
        int currentLimit = UtilsUI.readIntegerFromConsole("Enter the max limit where the wind speed is 100% safe for the drone, no wind tolerance");

        WindSpeed windSpeedF = new WindSpeed(previousLimit, currentLimit);
        PositionTolerance positionToleranceF = new PositionTolerance(0.0);
        Map<PositionTolerance, WindSpeed> config = new HashMap<>();
        config.put(positionToleranceF, windSpeedF);

        previousLimit = currentLimit;

        int choice = 1;

        while (choice == 1) {
            choice = UtilsUI.readIntegerFromConsole("Press 1 to continue configuration, or 0 to stop and declare the next range as unsafe");

            if (choice != 1) break;

            double position = UtilsUI.readDoubleFromConsole("Enter the next wind tolerance");

            currentLimit = UtilsUI.readIntegerFromConsole("Enter the max limit to fly with this tolerance: " + position);

            WindSpeed windSpeed = new WindSpeed(previousLimit, currentLimit);

            PositionTolerance positionTolerance = new PositionTolerance(position);

            config.put(positionTolerance, windSpeed);

            previousLimit = currentLimit;
        }

        int unsafeLimit = UtilsUI.readIntegerFromConsole("Enter the max limit where it is NOT safe to fly");

        WindSpeed windSpeed = new WindSpeed(unsafeLimit, 999);

        PositionTolerance positionTolerance = new PositionTolerance(-1);

        config.put(positionTolerance, windSpeed);

        System.out.println("\nPlease confirm the data:");
        System.out.println("=== " + modelName + " ===");

        for (Map.Entry<PositionTolerance, WindSpeed> entry : config.entrySet()) {
            double tolerance = entry.getKey().value();
            int min = entry.getValue().minSpeed();
            int max = entry.getValue().maxSpeed();

            if (tolerance == 0.0) {
                System.out.println("wind <= " + max + " -> 0m");
            } else if (tolerance == -1.0) {
                System.out.println(min + " < wind -> Not safe to fly");
            } else {
                System.out.println(min + " < wind <= " + max + " -> " + tolerance + "m");
            }
        }

        String confirm = UtilsUI.readLineFromConsole("Confirm (Y/N)? ");

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Operation canceled.");
            return false;
        }
        Configuration configuration = new Configuration(config);
        boolean success = controller.createModel(modelName, configuration);
        if (success) {
            System.out.println("Model successfully created!");
            return true;
        } else {
            System.out.println("Failed to create model.");
            return true;
        }
    }


    @Override
    public String headline() {
        return "Create New Drone Model";
    }
}
