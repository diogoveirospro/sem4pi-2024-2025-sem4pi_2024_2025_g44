package Shodrone.console.Model.printer;

import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Model;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class ModelPrinter implements Visitor<Model> {
    @Override
    public void visit(Model visitee) {
        System.out.printf("%s\n", UtilsUI.BOLD + UtilsUI.YELLOW + visitee.identity() + UtilsUI.RESET);
        System.out.printf("---------------------------------------------------------------\n\n");
        System.out.println(UtilsUI.BOLD + "Configuration: \n" + UtilsUI.RESET);
        System.out.printf(visitee.getConfiguration().toString() + "\n");
        System.out.printf("---------------------------------------------------------------\n");
    }
}
