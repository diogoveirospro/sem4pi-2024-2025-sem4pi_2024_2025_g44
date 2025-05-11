package Shodrone.console.Drone;

import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Model;
import eapli.framework.visitor.Visitor;

public class ModelPrinter implements Visitor<Model> {
    @Override
    public void visit(Model visitee) {
        System.out.printf("%s\n", visitee.identity());
    }
}
