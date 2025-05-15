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

public class RemoveDroneUI extends AbstractFancyUI {
    private final RemoveDroneController controller = new RemoveDroneController();

    @Override
    protected boolean doShow() {
        try {
            Drone drone = selectDrone();
            if (drone == null) return false;

            String removalReason = enterValidRemovalReason();

            System.out.println(UtilsUI.YELLOW + "\nConfirm removal of drone with:");
            System.out.println("Serial Number: " + drone.identity());
            System.out.println("Reason: " + removalReason);
            if (!UtilsUI.confirm("Do you want to proceed? (y/n)")) {
                throw new UserCancelledException(UtilsUI.YELLOW + "Operation cancelled by user." + UtilsUI.RESET);
            }

            boolean success = controller.removeDrone(drone, removalReason);
            if (success) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Drone removed successfully!" + UtilsUI.RESET);
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Drone not found or could not be removed." + UtilsUI.RESET);
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

    @Override
    public String headline() {
        return "Remove a Drone from the inventory";
    }

    private Drone selectDrone() {
        Iterable<Drone> drones = controller.listDrones();
        if (drones == null || !drones.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No drones available." + UtilsUI.RESET);
            return null;
        }

        List<Drone> droneList = new ArrayList<>();
        drones.forEach(droneList::add);

        ListWidget<Drone> droneListWidget = new ListWidget<>(headline(), droneList, new DronePrinter());
        droneListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(droneList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return droneList.get(option);
            }
        } while (true);
    }

    private String enterValidRemovalReason() {
        String reason;
        do {
            try {
                reason = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter Reason for Removal (or 'cancel' to abort): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(reason)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + "Action cancelled by user." + UtilsUI.RESET);
                }
                if (reason.length() < 5) {
                    throw new IllegalArgumentException("Reason must be at least 5 characters long.");
                }
                return reason;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }
}
