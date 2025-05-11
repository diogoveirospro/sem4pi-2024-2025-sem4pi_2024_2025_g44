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
        Map<Double, int[]> config = new HashMap<>();
        System.out.println("=== Create Drone Model ===");
        String name = UtilsUI.readLineFromConsole("Enter the model name");
        ModelName modelName = new ModelName(name);

        int choice = 1;
        int[] posTolerance = new int[1];
        posTolerance[0] = 0;
        posTolerance[1] = UtilsUI.readIntegerFromConsole("Enter the max limit where the wind speed is 100% safe for the drone, no wind tolerance");;
        config.put(0.0, posTolerance);

        while (choice == 1){
            choice = UtilsUI.readIntegerFromConsole("If you already finished the configuration, press 0 and to continue, press 1");
            if (choice != 1) break;

            double windTolerance = UtilsUI.readDoubleFromConsole("Enter the next wind tolerance, if you want to finish with unsafe press -1 ");
            if (windTolerance == -1) {
                posTolerance[0] = posTolerance[1];
                posTolerance[1] = UtilsUI.readIntegerFromConsole("Enter the max limit to not be safe to fly");
                config.put(-1.0, posTolerance);
                break;
            }
            posTolerance[0] = posTolerance[1];
            posTolerance[1] = UtilsUI.readIntegerFromConsole("Enter the max limit where the wind tolerance is: " + windTolerance);
            config.put(windTolerance, posTolerance);

        }

        System.out.println("\nPlease confirm the data:");

        System.out.println("\nPlease confirm the data:");
        System.out.println("=== "+ modelName +" ===");

        for (Map.Entry<Double, int[]> entry : config.entrySet()) {
            double tolerance = entry.getKey();
            int[] range = entry.getValue();
            String rangeText = range[1] == Integer.MAX_VALUE
                    ? (range[0] + " < wind")
                    : (range[0] + " < wind <= " + range[1]);

            String msg = (tolerance == 0.0)
                    ? rangeText + " -> Safe (0m)"
                    : rangeText + " -> " + tolerance + "m";

            System.out.println(msg);
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
        return null;
    }
}
