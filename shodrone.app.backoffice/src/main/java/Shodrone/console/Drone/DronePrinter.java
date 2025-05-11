package Shodrone.console.Drone;

import core.Drone.domain.Entities.Drone;
import eapli.framework.visitor.Visitor;

public class DronePrinter implements Visitor<Drone> {

    @Override
    public void visit(Drone visitee) {
        System.out.printf("%s\n", visitee.identity());
    }
}

