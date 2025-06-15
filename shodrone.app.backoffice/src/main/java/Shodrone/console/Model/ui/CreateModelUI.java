package Shodrone.console.Model.ui;

import Shodrone.exceptions.UserCancelledException;
import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Name;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * UI for creating a new drone model.
 *
 * Allows the user to define a model name and configure wind speed tolerances
 * mapped to positional tolerances. The resulting configuration is used to
 * create a model through the controller.
 */
public class CreateModelUI extends AbstractFancyUI {

    private final CreateModelController controller = new CreateModelController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Main method that controls the UI logic for creating a new drone model.
     *
     * @return true if the model was created successfully, false otherwise.
     */
    @Override
    public boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)) {
                // Prompt for model name
                ModelName modelName = enterValidModelName();

                // Start by defining the first wind speed tolerance range (from 0 to X)
                int previousLimit = 0;
                int currentLimit;

                currentLimit = defineCurrentLimit(previousLimit);

                WindSpeed windSpeedF = new WindSpeed(previousLimit, currentLimit);
                PositionTolerance positionToleranceF = new PositionTolerance(0.0);
                Map<WindSpeed, PositionTolerance> config = new LinkedHashMap<>();
                config.put(windSpeedF, positionToleranceF);

                previousLimit = currentLimit;
                double lastPosition = 0.0;

                // Loop to allow the user to add multiple wind tolerance configurations
                previousLimit = allPositionToleranceConfiguration(lastPosition, previousLimit, config);

                // Final wind range with UNSAFE indication
                WindSpeed windSpeed = new WindSpeed(previousLimit, 999);
                PositionTolerance positionTolerance = new PositionTolerance(-1);
                config.put(windSpeed, positionTolerance);

                TimeLimit timeLimit = inputTimeLimit();
                // Print summary of the configuration for confirmation
                summaryOfConfiguration(modelName, config);

                // Confirm creation
                boolean confirm = UtilsUI.confirm("Confirm? (Y/N): ");
                if (!confirm) {
                    System.out.println(UtilsUI.BOLD + UtilsUI.RED + "\nCanceled." + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return false;
                }

                // Attempt to create the model
                Configuration configuration = new Configuration(config, SafetyStatus.SAFE);
                boolean success = controller.createModel(modelName, configuration, timeLimit);

                System.out.println(success ? UtilsUI.GREEN + UtilsUI.BOLD + "\nModel created!" + UtilsUI.RESET :
                        UtilsUI.BOLD + UtilsUI.RED + "\nFailed to create model." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    private static void summaryOfConfiguration(ModelName modelName, Map<WindSpeed, PositionTolerance> config) {
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
    }

    private static int allPositionToleranceConfiguration(double lastPosition, int previousLimit, Map<WindSpeed, PositionTolerance> config) {
        int currentLimit;
        while (true) {
            if (!UtilsUI.confirm(UtilsUI.BOLD + "Add more wind tolerances? (Y/N): " + UtilsUI.RESET)) {
                break;
            }

            // Request positional tolerance, ensuring it's increasing
            double position = definePositionTolerance(lastPosition);

            // Request wind limit for this tolerance, ensuring it's increasing
            currentLimit = requestWindLimit(previousLimit, position);

            WindSpeed windSpeed = new WindSpeed(previousLimit, currentLimit);
            PositionTolerance positionTolerance = new PositionTolerance(position);

            config.put(windSpeed, positionTolerance);
            previousLimit = currentLimit;
            lastPosition = position;
        }
        return previousLimit;
    }

    private static int requestWindLimit(int previousLimit, double position) {
        int currentLimit;
        do {
            try {
                System.out.println(UtilsUI.YELLOW + "\nYou can cancel by typing -1." + UtilsUI.RESET);
                currentLimit = UtilsUI.readIntegerFromConsole(UtilsUI.BOLD + "Max wind (for " + position + "m): " + UtilsUI.RESET);
                if (currentLimit == -1){
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                if (currentLimit <= previousLimit) {
                    System.out.println(UtilsUI.RED + "\nMust be > " + previousLimit + UtilsUI.RESET);
                } else {
                    return currentLimit;
                }
            } catch (NumberFormatException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid input. Please enter a valid decimal number." + UtilsUI.RESET);
            }
        } while (true);
    }

    private static double definePositionTolerance(double lastPosition) {
        double position;
        do {
            try {
                System.out.println(UtilsUI.YELLOW + "\nYou can cancel by typing -1" + UtilsUI.RESET);
                position = UtilsUI.readDoubleFromConsole(UtilsUI.BOLD + "Wind tolerance (> " + lastPosition + "): " + UtilsUI.RESET);

                if (position == -1){
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                if (position <= lastPosition) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n Must be > " + lastPosition + "\n"+ UtilsUI.RESET);
                } else {
                    return position;
                }
            } catch (NumberFormatException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid input. Please enter a valid decimal number." + UtilsUI.RESET);
            }
        } while (true);
    }

    private static int defineCurrentLimit(int previousLimit) {
        int currentLimit;
        do {
            try {
                currentLimit = UtilsUI.readIntegerFromConsole(UtilsUI.BOLD + "Max wind (safe) (or type -1 to go back): " + UtilsUI.RESET);
                if (currentLimit == -1){
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                if (currentLimit <= previousLimit) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Must be > " + previousLimit + UtilsUI.RESET);
                } else {
                    return currentLimit;
                }
            } catch (NumberFormatException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid input. Please enter a valid integer." + UtilsUI.RESET);
            }
        } while (true);
    }

    private ModelName enterValidModelName() {
        String name;
        do {
            try {
                name = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the model name (or type 'cancel' to go back): " + UtilsUI.RESET);
                assert name != null;
                if ("cancel".equalsIgnoreCase(name)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("\nName cannot be empty.\n");
                }
                return new ModelName(name);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);

    }

    private TimeLimit inputTimeLimit() {
        String input;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm"); // permite mais de 24 horas ao tratar manualmente
        do {
            try {
                input = UtilsUI.readLineFromConsole(UtilsUI.BOLD + UtilsUI.BLUE +
                        "\nEnter time limit in HH:mm format (can exceed 24h, e.g. 30:15) or type 'cancel' to exit: " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(input)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                String[] parts = input.split(":");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid format. Expected HH:mm.");
                }

                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);

                if (hours < 0 || minutes < 0 || minutes >= 60) {
                    throw new IllegalArgumentException("Hours must be >= 0 and minutes between 0 and 59.");
                }

                Duration duration = Duration.ofHours(hours).plusMinutes(minutes);

                if (duration.isZero() || duration.isNegative()) {
                    throw new IllegalArgumentException("Time limit must be greater than 0.");
                }

                return new TimeLimit(duration);

            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid time format. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }
    /**
     * Returns the title/headline of the UI screen.
     *
     * @return The headline text
     */
    @Override
    public String headline() {
        return "Create New Drone Model";
    }
}
