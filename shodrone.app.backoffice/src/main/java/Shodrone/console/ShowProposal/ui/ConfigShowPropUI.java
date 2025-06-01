package Shodrone.console.ShowProposal.ui;

import Shodrone.console.Drone.ui.ListDroneUI;
import Shodrone.console.Model.printer.ModelPrinter;
import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import core.ShowProposal.application.ConfigShowPropController;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.*;

public class ConfigShowPropUI extends AbstractFancyUI {

    private final ConfigShowPropController controller = new ConfigShowPropController();

    @Override
    protected boolean doShow() {
        try {
            ShowProposal showProposal = selectProposal();
            if (showProposal == null) return false;

            ShowConfiguration.Builder configBuilder = new ShowConfiguration.Builder();
            boolean configuring = true;
            Scanner scanner = new Scanner(System.in);

            Map<Model, Integer> verificaçao = new LinkedHashMap<>();
            while (configuring) {
                int AllQuantity;
                Model model = selectModel();
                if (model == null) continue;
                if (verificaçao.containsKey(model)) {
                    AllQuantity = verificaçao.get(model);
                }else{
                    AllQuantity = 0;
                }
                Iterable<Drone> drones = controller.getDrnModelList(model);
                List<Drone> droneList = new ArrayList<>();
                drones.forEach(droneList::add);
                if (droneList.isEmpty()) {
                    System.out.println(UtilsUI.RED + "No drones available for model '" + model.identity() + "'." + UtilsUI.RESET);
                    continue;
                }
                int availableDrones  = droneList.size() - AllQuantity;
                if (availableDrones <= 0) {
                    System.out.println(UtilsUI.RED + "No more drones available for model '" + model.identity() + "'." + UtilsUI.RESET);
                    continue;
                }

                System.out.print(UtilsUI.BOLD + "Enter the number of drones for model '" + model.identity() + " (" + availableDrones  + " drones available) : " + UtilsUI.RESET);
                int quantity;
                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity <= 0) {
                        System.out.println(UtilsUI.RED + "Invalid quantity!" + UtilsUI.RESET);
                        continue;
                    }
                    if (quantity > availableDrones) {
                        System.out.println(UtilsUI.RED + "Not enough drones available. Maximum is " + droneList.size() + "." + UtilsUI.RESET);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(UtilsUI.RED + "Please enter a valid number." + UtilsUI.RESET);
                    continue;
                }
                List<Drone> selectedDrones = droneList.subList(AllQuantity, AllQuantity + quantity);

                configBuilder.addDrones(model, selectedDrones);
                verificaçao.put(model, AllQuantity + quantity);

                System.out.print("Add another model? (y/n): ");
                String more = scanner.nextLine().trim().toLowerCase();
                if (!more.equals("y")) {
                    configuring = false;
                }
            }

            // Mostrar resumo
            System.out.println(UtilsUI.BOLD + "\nConfiguration Summary:" + UtilsUI.RESET);
            configBuilder.getConfig().forEach((model, drones) ->
                    System.out.printf(" - Model: %s, Quantity: %d%n", model.identity(), drones.size()));

            System.out.print("\nConfirm configuration? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!confirm.equals("y")) {
                System.out.println(UtilsUI.YELLOW + "Configuration cancelled." + UtilsUI.RESET);
                return false;
            }

            boolean success = controller.configureShow(showProposal, configBuilder);
            if (success) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nShow configuration saved successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to save configuration." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.BOLD + UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    /**
     * Returns the headline/title of the UI screen.
     *
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "Add a Drone to the Inventory";
    }

    /**
     * Prompts the user to select a model from the list of available drone models.
     *
     * @return the selected {@link Model} or null if none are available.
     * @throws UserCancelledException if the user cancels the action.
     */
    private ShowProposal selectProposal() {
        Iterable<ShowProposal> showProposals = controller.getShowProposalList();

        if (showProposals == null || !showProposals.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo show proposal available." + UtilsUI.RESET);
            return null;
        }

        List<ShowProposal> showProposalList = new ArrayList<>();
        showProposals.forEach(showProposalList::add);

        ListWidget<ShowProposal> showProposalListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\nChoose a Model: \n" +
                UtilsUI.RESET, showProposalList, new ShowProposalPrinter());
        showProposalListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(showProposalList);

            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return showProposalList.get(option);
            }
        } while (true);
    }

    private Model selectModel() {
        Iterable<Model> models = controller.getModelList();

        if (models == null || !models.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo models available." + UtilsUI.RESET);
            return null;
        }

        List<Model> modelList = new ArrayList<>();
        models.forEach(modelList::add);

        ListWidget<Model> modelListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\nChoose a Model: \n" +
                UtilsUI.RESET, modelList, new ModelPrinter());
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