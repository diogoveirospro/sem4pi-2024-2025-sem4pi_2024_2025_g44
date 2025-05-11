package Shodrone.console.Drone;

import core.Drone.application.ListDroneController;
import core.ModelOfDrone.domain.Entities.Model;
import core.Drone.domain.Entities.Drone;
import eapli.framework.presentation.console.AbstractUI;
import shodrone.presentation.UtilsUI;

import java.util.List;

public class ListDroneUI extends AbstractUI {

    private final ListDroneController controller = new ListDroneController();

    @Override
    protected boolean doShow() {
        try {
            // Pedir ao controller os modelos disponíveis
            List<Model> modelList = controller.getModelList();
            if (modelList.isEmpty()) {
                System.out.println(UtilsUI.YELLOW + "No drone models found in the system." + UtilsUI.RESET);
                return false;
            }

            System.out.println(UtilsUI.BOLD + "\nAvailable Drone Models:" + UtilsUI.RESET);
            int i = 1;
            for (Model model : modelList) {
                System.out.println("  [" + i++ + "] " + model.toString());
            }

                int selectedIndex = UtilsUI.readInt("\nSelect a model by number: ", 1, modelList.size());
            Model selectedModel = modelList.get(selectedIndex - 1);
            System.out.println(UtilsUI.YELLOW + "\nYou selected model: " + selectedModel + UtilsUI.RESET);

            if (!UtilsUI.confirm("Do you want to continue? (y/n)")) {
                System.out.println(UtilsUI.YELLOW + "Operation cancelled by user." + UtilsUI.RESET);
                return false;
            }

            // Obter os drones ativos desse modelo
            List<Drone> droneList = controller.getDrnModelList(selectedModel);
            if (droneList.isEmpty()) {
                System.out.println(UtilsUI.RED + "No active drones found for the selected model." + UtilsUI.RESET);
            } else {
                System.out.println(UtilsUI.GREEN + "\nActive Drones of Model: " + selectedModel.identity() + UtilsUI.RESET);
                droneList.forEach(d -> System.out.println("  -> " + d));
            }

            UtilsUI.goBackAndWait();
            return true;

        } catch (Exception e) {
            System.out.println(UtilsUI.RED + "Error while listing drones: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return "List Active Drones by Model";
    }
}
