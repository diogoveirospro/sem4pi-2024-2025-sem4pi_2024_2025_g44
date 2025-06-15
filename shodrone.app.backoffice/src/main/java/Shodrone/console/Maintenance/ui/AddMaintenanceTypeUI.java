package Shodrone.console.Maintenance.ui;

import Shodrone.exceptions.UserCancelledException;
import core.Maintenance.application.AddMaintenanceTypeController;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

public class AddMaintenanceTypeUI extends AbstractFancyUI {

    private final AddMaintenanceTypeController controller = new AddMaintenanceTypeController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)) {


                String maintenanceDescription = maintenanceDescription();

                Boolean resetUsageTime = maintenanceReset();

                controller.addMaintenanceType(maintenanceDescription, resetUsageTime);

                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nAdd Maintenance Type successes!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }catch (UserCancelledException e) {
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private Boolean maintenanceReset() {
        String resetUsageTime;

        do {
            try {
                resetUsageTime = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nReset Usage Time? (yes/no) (or type 'cancel' to exit): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(resetUsageTime)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }else {
                    if (resetUsageTime.isBlank()) {
                        throw new IllegalArgumentException("Reset Usage Time cannot be empty.");
                    } else if (!resetUsageTime.equalsIgnoreCase("yes") && !resetUsageTime.equalsIgnoreCase("no")) {
                        throw new IllegalArgumentException("Please enter 'yes' or 'no'.");
                    }
                    return resetUsageTime.equalsIgnoreCase("yes");
                }
            }catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please enter 'yes' or 'no'." + UtilsUI.RESET);
            }

        } while (true);
    }

    private String maintenanceDescription() {

        String description;

        do {
            try {
                description = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter Maintenance Type Description (or type 'cancel' to exit): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(description)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }else {
                    if (description.isBlank()) {
                        throw new IllegalArgumentException("Description cannot be empty.");
                    }
                    return  description;
                }
            }catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid description. Please try again." + UtilsUI.RESET);
            }

        } while (true);


    }

    @Override
    public String headline() {
        return "Add Maintenance Type";
    }
}