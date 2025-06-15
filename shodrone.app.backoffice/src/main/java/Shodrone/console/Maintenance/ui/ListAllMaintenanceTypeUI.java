package Shodrone.console.Maintenance.ui;

import Shodrone.console.Maintenance.printer.MaintenanceTypePrinter;
import core.Maintenance.application.ListAllMaintenanceTypeController;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class ListAllMaintenanceTypeUI extends AbstractFancyUI {

    private final ListAllMaintenanceTypeController controller = new ListAllMaintenanceTypeController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MaintenanceTypePrinter maintenanceTypePrinter = new MaintenanceTypePrinter();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)) {

                Iterable<MaintenanceType> maintenanceType = controller.findAllMaintenanceTypes();
                if (maintenanceType == null || !maintenanceType.iterator().hasNext()) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Maintenance Type available." + UtilsUI.RESET);
                    return false;
                }

                List<MaintenanceType> maintenanceTypeArrayList = new ArrayList<>();
                maintenanceType.forEach(maintenanceTypeArrayList::add);

                ListWidget<MaintenanceType> droneListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                        "\n" + UtilsUI.RESET, maintenanceTypeArrayList, maintenanceTypePrinter);
                droneListWidget.show();
                UtilsUI.goBackAndWait();
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    @Override
    public String headline() {
        return UtilsUI.BOLD + String.format("%-20s | %-10s |",
                "Maintenance Type", "Reset Usage Time") + "\n"
                + String.format("%-20s-+-%-10s-+", "-".repeat(20), "-".repeat(10))
                + UtilsUI.RESET;
    }

}