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

        String name = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Model name: " + UtilsUI.RESET);
        ModelName modelName = new ModelName(name);

        int previousLimit = 0;
        int currentLimit;

        do {
            currentLimit = UtilsUI.readIntegerFromConsole(UtilsUI.BOLD + "Max wind (safe): " + UtilsUI.RESET);
            if (currentLimit <= previousLimit) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Must be > " + previousLimit + UtilsUI.RESET);
            }
        } while (currentLimit <= previousLimit);

        WindSpeed windSpeedF = new WindSpeed(previousLimit, currentLimit);
        PositionTolerance positionToleranceF = new PositionTolerance(0.0);
        Map<WindSpeed, PositionTolerance> config = new LinkedHashMap<>();
        config.put(windSpeedF, positionToleranceF);

        previousLimit = currentLimit;
        double lastPosition = 0.0;
        int choice;

        while (true) {
            if (!UtilsUI.confirm(UtilsUI.BOLD + "Add more wind tolerances? (Y/N): " + UtilsUI.RESET)) {
                break;
            }

            double position;
            do {
                position = UtilsUI.readDoubleFromConsole(UtilsUI.BOLD + "Wind tolerance (> " + lastPosition + "): " + UtilsUI.RESET);
                if (position <= lastPosition) {
                    System.out.println(UtilsUI.RED + "\nMust be > " + lastPosition + UtilsUI.RESET);
                }
            } while (position <= lastPosition);

            do {
                currentLimit = UtilsUI.readIntegerFromConsole(UtilsUI.BOLD + "Max wind (for " + position + "m): " + UtilsUI.RESET);
                if (currentLimit <= previousLimit) {
                    System.out.println(UtilsUI.RED + "\nMust be > " + previousLimit + UtilsUI.RESET);
                }
            } while (currentLimit <= previousLimit);

            WindSpeed windSpeed = new WindSpeed(previousLimit, currentLimit);
            PositionTolerance positionTolerance = new PositionTolerance(position);

            config.put(windSpeed, positionTolerance);
            previousLimit = currentLimit;
            lastPosition = position;
        }

        WindSpeed windSpeed = new WindSpeed(previousLimit, 999);
        PositionTolerance positionTolerance = new PositionTolerance(-1);
        config.put(windSpeed, positionTolerance);

        System.out.println("\n" + UtilsUI.BOLD + UtilsUI.YELLOW + "== " + modelName + " ==" + UtilsUI.RESET);
        for (Map.Entry<WindSpeed, PositionTolerance> entry : config.entrySet()) {
            int min = entry.getKey().minSpeed();
            int max = entry.getKey().maxSpeed();
            double tolerance = entry.getValue().value();

            if (tolerance == 0.0) {
                System.out.println("wind <= " + max + " -> 0m");
            } else if (tolerance == -1.0) {
                System.out.println(min + " < wind -> UNSAFE");
            } else {
                System.out.println(min + " < wind <= " + max + " -> " + tolerance + "m");
            }
        }

        boolean confirm = UtilsUI.confirm("Confirm? (Y/N): ");

        if (!confirm) {
            System.out.println(UtilsUI.BOLD + UtilsUI.RED + "Canceled." + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }

        Configuration configuration = new Configuration(config, SafetyStatus.SAFE);
        boolean success = controller.createModel(modelName, configuration);

        System.out.println(success ? UtilsUI.GREEN + UtilsUI.BOLD + "\nModel created!" + UtilsUI.RESET :
                UtilsUI.BOLD + UtilsUI.RED + "\nFailed to create model." + UtilsUI.RESET);
        UtilsUI.goBackAndWait();
        return true;
    }


    @Override
    public String headline() {
        return "Create New Drone Model";
    }
}
