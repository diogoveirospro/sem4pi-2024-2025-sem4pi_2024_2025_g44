package Shodrone.console.Drone;

import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.ListDroneController;
import core.ModelOfDrone.domain.Entities.Model;
import core.Drone.domain.Entities.Drone;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class ListDroneUI extends AbstractFancyListUI<Drone> {
    private final ListDroneController controller = new ListDroneController();

    @Override
    protected boolean doShow() {
        try {
            super.doShow();
            UtilsUI.goBackAndWait();
            return true;
        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String headline() {
        return "List Active Drones by Model";
    }

    @Override
    protected Iterable<Drone> elements() {
        Model model = selectModel();
        if (model == null) {
            return new ArrayList<>();
        }
        return controller.getDrnModelList(model);
    }

    @Override
    protected Visitor<Drone> elementPrinter() {
        return new DronePrinter();
    }

    @Override
    protected String elementName() {
        return "Drone";
    }

    @Override
    protected String listHeader() {
        return "List of Drone of a type";
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "No drones available." + UtilsUI.RESET;
    }

    private Model selectModel() {
        Iterable<Model> models = controller.listModels();
        if (models == null || !models.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No models available." + UtilsUI.RESET);
            return null;
        }

        List<Model> modelList = new ArrayList<>();
        models.forEach(modelList::add);

        ListWidget<Model> modelListWidget = new ListWidget<>("Choose a Model", modelList, new ModelPrinter());
        modelListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(modelList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return modelList.get(option);
            }
        } while (true);
    }
}
