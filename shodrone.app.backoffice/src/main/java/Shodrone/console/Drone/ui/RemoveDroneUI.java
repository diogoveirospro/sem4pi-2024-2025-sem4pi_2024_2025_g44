package Shodrone.console.Drone.ui;

import Shodrone.console.Drone.printer.DronePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.RemoveDroneController;
import core.Drone.domain.Entities.Drone;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * User Interface for removing a drone from the inventory.
 *
 * Allows the user to select a drone, provide a reason for removal,
 * confirm the action, and process the removal through the controller.
 */
public class RemoveDroneUI extends AbstractFancyUI {

    private final RemoveDroneController controller = new RemoveDroneController();

    /**
     * Executes the UI logic for removing a drone.
     *
     * @return true if the drone was removed successfully; false otherwise.
     */
    @Override
    protected boolean doShow() {
        try {
            Drone drone = selectDrone();
            if (drone == null) return false;

            String removalReason = enterValidRemovalReason();

            System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nConfirm removal of drone with:" + UtilsUI.RESET);
            System.out.println("Serial Number: " + drone.identity());
            System.out.println("Reason: " + removalReason);
            System.out.println("Safety Status: " + drone.droneStatus());

            if (!UtilsUI.confirm("Do you want to proceed? (Y/N)")) {
                throw new UserCancelledException(UtilsUI.RED + "Operation cancelled by user." + UtilsUI.RESET);
            }

            boolean success = controller.removeDrone(drone, removalReason);
            if (success) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nDrone removed successfully!" + UtilsUI.RESET);
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nDrone not found or could not be removed." + UtilsUI.RESET);
            }

            UtilsUI.goBackAndWait();
            return success;

        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            UtilsUI.goBackAndWait();
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + "Invalid input: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + "Unexpected error: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    /**
     * Returns the headline/title for this UI screen.
     *
     * @return String representing the UI title
     */
    @Override
    public String headline() {
        return "Remove a Drone from the Inventory";
    }

    /**
     * Allows the user to select a drone from a list of available drones.
     * Displays each drone with its details and prompts for index selection.
     *
     * @return The selected {@link Drone}, or null if none available
     * @throws UserCancelledException if the user cancels the selection
     */
    private Drone selectDrone() {
        Iterable<Drone> drones = controller.listDrones();
        if (drones == null || !drones.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo drones available!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return null;
        }

        List<Drone> droneList = new ArrayList<>();
        int index = 1;

        // Print table header
        System.out.println(UtilsUI.BOLD
                + String.format("%-5s | %-20s | %-20s | %-10s |", "INDEX", "SERIAL", "MODEL", "STATUS") + "\n"
                + String.format("%-5s-+-%-20s-+-%-20s-+-%-10s-+", "-".repeat(5), "-".repeat(20), "-".repeat(20), "-".repeat(10))
                + UtilsUI.RESET);

        // List all drones with index
        for (Drone drone : drones) {
            System.out.printf("%-5d | ", index++);
            new DronePrinter().visit(drone);
            droneList.add(drone);
        }

        // Prompt user to choose
        int option;
        do {
            option = UtilsUI.selectsIndex(droneList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option < 0 || option >= droneList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return droneList.get(option);
            }
        } while (true);
    }

    /**
     * Prompts the user to input a valid reason for drone removal.
     * Valid reasons must be at least 5 characters. User may cancel by typing 'cancel'.
     *
     * @return A valid reason string
     * @throws UserCancelledException if the user cancels the input
     */
    private String enterValidRemovalReason() {
        String reason;
        do {
            try {
                reason = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter reason for removal (or 'cancel' to abort): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(reason)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + "Action cancelled by user." + UtilsUI.RESET);
                }
                if (reason.trim().length() < 5) {
                    throw new IllegalArgumentException("Reason must be at least 5 characters long.");
                }
                return reason;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }
}
