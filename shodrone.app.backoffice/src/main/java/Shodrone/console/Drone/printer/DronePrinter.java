package Shodrone.console.Drone.printer;

import core.Drone.domain.Entities.Drone;
import eapli.framework.visitor.Visitor;

public class DronePrinter implements Visitor<Drone> {
    @Override
    public void visit(Drone drone) {
        System.out.println("Serial Number: " + drone.identity().toString());
        System.out.println("Model: " + drone.model().identity());
        System.out.println("Drone Status: " + drone.droneStatus().toString());
        System.out.println("-------------------------------");
    }
}

