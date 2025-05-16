package Shodrone.console.Drone.printer;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class DronePrinter implements Visitor<Drone> {
    @Override
    public void visit(Drone drone) {
        String statusPlain = String.format("%-10s", drone.droneStatus().equals(DroneStatus.ACTIVE) ? "Active" : drone.droneStatus().toString());

        String statusColored = drone.droneStatus().equals(DroneStatus.ACTIVE)
                ? UtilsUI.GREEN + statusPlain + UtilsUI.RESET
                : UtilsUI.RED + statusPlain + UtilsUI.RESET;

        System.out.printf(
                "%-20s | %-20s | %-10s |\n",
                drone.identity().toString(),
                drone.model().identity().toString(),
                statusColored
        );
    }
}
