package Shodrone.console.Drone;

import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.RemoveDroneController;
import core.Drone.domain.ValueObjects.SerialNumber;
import eapli.framework.presentation.console.AbstractUI;
import shodrone.presentation.UtilsUI;

import java.util.regex.Pattern;

public class RemoveDroneUI extends AbstractUI {

    private final RemoveDroneController controller = new RemoveDroneController();

    @Override
    protected boolean doShow() {
        try {
            SerialNumber serialNumber = enterValidSerialNumber();
            String removalReason = enterValidRemovalReason();

            System.out.println(UtilsUI.YELLOW + "\nConfirm removal of drone with:");
            System.out.println("Serial Number: " + serialNumber);
            System.out.println("Reason: " + removalReason);
            if (!UtilsUI.confirm("Do you want to proceed? (y/n)")) {
                throw new UserCancelledException(UtilsUI.YELLOW + "Operation cancelled by user." + UtilsUI.RESET);
            }

            boolean success = controller.removeDrone(serialNumber, removalReason);
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

    private SerialNumber enterValidSerialNumber() {
        String input;
        String serialRegex = "^[A-Z0-9-]{3,30}$"; // Ajusta conforme necessário
        do {
            try {
                input = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter Serial Number (or 'cancel' to abort): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(input)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + "Action cancelled by user." + UtilsUI.RESET);
                }
                if (!Pattern.matches(serialRegex, input)) {
                    throw new IllegalArgumentException("Invalid serial number format.");
                }
                return new SerialNumber(input);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
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

    @Override
    public String headline() {
        return "Remove Drone from Inventory";
    }
}
