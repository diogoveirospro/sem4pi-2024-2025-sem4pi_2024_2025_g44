package Shodrone.console.Drone.printer;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import eapli.framework.visitor.Visitor;
import org.aspectj.weaver.Utils;
import shodrone.presentation.UtilsUI;

public class DronePrinter implements Visitor<Drone> {
    @Override
    public void visit(Drone drone) {
        System.out.printf(
                "%s - %s - %s",
                drone.identity().toString(),
                drone.model().identity().toString(),
                drone.droneStatus() == DroneStatus.ACTIVE ? UtilsUI.GREEN + UtilsUI.BOLD + drone.droneStatus().toString() +
                        UtilsUI.RESET : UtilsUI.RED + UtilsUI.BOLD + drone.droneStatus().toString() + UtilsUI.RESET
        );
    }
}
