package Shodrone.console.Drone;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.*;

public class CreateModelUI extends AbstractFancyUI {

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean doShow() {
        Scanner scanner = new Scanner(System.in);
        Map<Double, int[]> config = new LinkedHashMap<>();

        System.out.println("=== Create Drone Model ===");
        String name = UtilsUI.readLineFromConsole("Enter the model name");
        ModelName modelName = new ModelName(name);

        int previousLimit = 0;
        int currentLimit = UtilsUI.readIntegerFromConsole("Enter the max limit where the wind speed is 100% safe for the drone, no wind tolerance");

        config.put(0.0, new int[] {previousLimit, currentLimit});
        previousLimit = currentLimit;

        int choice = 1;

        while (choice == 1) {
            choice = UtilsUI.readIntegerFromConsole("Press 1 to continue configuration, or 0 to stop and declare the next range as unsafe");

            if (choice != 1) break;

            double windTolerance = UtilsUI.readDoubleFromConsole("Enter the next wind tolerance");

            currentLimit = UtilsUI.readIntegerFromConsole("Enter the max limit to fly with this tolerance: " + windTolerance);

            // Cria novo array com intervalo e adiciona ao mapa
            config.put(windTolerance, new int[] {previousLimit, currentLimit});
            previousLimit = currentLimit;
        }

        int unsafeLimit = UtilsUI.readIntegerFromConsole("Enter the max limit where it is NOT safe to fly");
        config.put(-1.0, new int[] {previousLimit, unsafeLimit});

        System.out.println("\nPlease confirm the data:");
        System.out.println("=== " + modelName + " ===");

        for (Map.Entry<Double, int[]> entry : config.entrySet()) {
            double tolerance = entry.getKey();
            int[] range = entry.getValue();

            if (tolerance == 0.0) {
                System.out.println("wind <= " + range[1] + " -> 0m");
            } else if (tolerance == -1.0) {
                System.out.println(range[0] + " < wind -> Not safe to fly");
            } else {
                System.out.println(range[0] + " < wind <= " + range[1] + " -> " + tolerance + "m");
            }
        }
        String confirm = UtilsUI.readLineFromConsole("Confirm (Y/N)? ");
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Operation canceled.");
            return false;
        }

        boolean success = controller.createModel(modelName, config);
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
