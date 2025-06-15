package Shodrone.console.Maintenance.ui;

import Shodrone.console.Maintenance.printer.MaintenanceTypePrinter;
import core.Maintenance.application.ListAllMaintenanceTypeController;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

/**
 * UI para listar todos os tipos de manutenção.
 */
public class ListAllMaintenanceTypeUI extends AbstractFancyListUI<MaintenanceType> {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListAllMaintenanceTypeController controller = new ListAllMaintenanceTypeController();

    @Override
    public boolean doShow() {
        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)) {
            final Iterable<MaintenanceType> types = elements();
            if (!types.iterator().hasNext()) {
                System.out.println(emptyMessage());
                UtilsUI.goBackAndWait();
                return true;
            }

            System.out.println(listHeader());
            for (MaintenanceType type : types) {
                elementPrinter().visit(type);
            }

            UtilsUI.goBackAndWait();
            return true;
        }
        return false;
    }

    @Override
    protected Iterable<MaintenanceType> elements() {
        return controller.findAllMaintenanceTypes();
    }

    @Override
    protected Visitor<MaintenanceType> elementPrinter() {
        return new MaintenanceTypePrinter();
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("%-20s | %-20s |", "Maintenance Type", "Reset Usage Time") + "\n"
                + String.format("%-20s-+-%-20s-+", "-".repeat(20), "-".repeat(20))
                + UtilsUI.RESET;
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "No Maintenance Type available." + UtilsUI.RESET;
    }

    @Override
    public String headline() {
        return "List All Maintenance Types";
    }
}