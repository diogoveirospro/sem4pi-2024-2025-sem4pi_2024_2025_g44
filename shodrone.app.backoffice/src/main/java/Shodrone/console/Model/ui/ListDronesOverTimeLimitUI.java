package Shodrone.console.Model.ui;

import Shodrone.console.Drone.printer.DronePrinter;
import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.application.ListDronesOverTimeLimitController;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class ListDronesOverTimeLimitUI extends AbstractFancyUI {

    private final ListDronesOverTimeLimitController controller = new ListDronesOverTimeLimitController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DronePrinter dronePrinter = new DronePrinter();


    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)) {
                Iterable<Drone> drones = controller.listDronesOverTimeLimit();
                if (!drones.iterator().hasNext()) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n No drones found over the time limit." + UtilsUI.RESET);
                    return false;
                }
                List<Drone> droneList = new ArrayList<>();
                drones.forEach(droneList::add);
                ListWidget<Drone> droneListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                        "List Drones\n" + UtilsUI.RESET, droneList, dronePrinter);
                droneListWidget.show();

                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("An error occurred while listing drones: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String headline() {
        return "List Drones Over Time Limit";
    }


}
