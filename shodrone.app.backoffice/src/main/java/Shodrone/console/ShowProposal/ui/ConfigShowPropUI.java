package Shodrone.console.ShowProposal.ui;

import Shodrone.console.Model.printer.ModelPrinter;
import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Model;
import core.ShowProposal.application.ConfigShowPropController;
import core.ShowProposal.domain.Entities.ShowConfigurationBuilder;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.Entities.ShowConfigurationEntry;
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

            ShowConfigurationBuilder configBuilder = new ShowConfigurationBuilder();
            boolean configuring = true;
            Scanner scanner = new Scanner(System.in);

            int remainingToConfigure = showProposal.quantityOfDrones().getQuantityOfDrones();
            Map<Model, Integer> verification = new LinkedHashMap<>();

            while (configuring && remainingToConfigure > 0) {
                System.out.println(UtilsUI.CYAN + "\nRemaining drones to configure: " + remainingToConfigure + UtilsUI.RESET);

                Model model = selectModel();
                if (model == null) continue;

                int alreadySelected = verification.getOrDefault(model, 0);
                Iterable<Drone> drones = controller.getDrnModelList(model);
                List<Drone> droneList = new ArrayList<>();
                drones.forEach(droneList::add);

                if (droneList.isEmpty()) {
                    System.out.println(UtilsUI.RED + "No drones available for model '" + model.identity() + "'." + UtilsUI.RESET);
                    continue;
                }

                int availableDrones = droneList.size() - alreadySelected;
                if (availableDrones <= 0) {
                    System.out.println(UtilsUI.RED + "No more drones available for model '" + model.identity() + "'." + UtilsUI.RESET);
                    continue;
                }

                int maxAllowed = Math.min(availableDrones, remainingToConfigure);
                System.out.print(UtilsUI.BOLD + "Enter number of drones for model '" + model.identity() + "' (Max allowed: " + maxAllowed + "): " + UtilsUI.RESET);

                int quantity;
                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity <= 0) {
                        System.out.println(UtilsUI.RED + "Invalid quantity!" + UtilsUI.RESET);
                        continue;
                    }
                    if (quantity > maxAllowed) {
                        System.out.println(UtilsUI.RED + "You can assign at most " + maxAllowed + " drones." + UtilsUI.RESET);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(UtilsUI.RED + "Please enter a valid number." + UtilsUI.RESET);
                    continue;
                }

                List<Drone> selectedDrones = droneList.subList(alreadySelected, alreadySelected + quantity);
                for (Drone drone : selectedDrones) {
                    configBuilder.addDrones(new ShowConfigurationEntry(model, drone));
                }

                verification.put(model, alreadySelected + quantity);
                remainingToConfigure -= quantity;

                if (remainingToConfigure <= 0) {
                    System.out.println(UtilsUI.GREEN + "Drone requirement fulfilled." + UtilsUI.RESET);
                    break;
                }

                System.out.print("Add another model? (y/n): ");
                String more = scanner.nextLine().trim().toLowerCase();
                if (!more.equals("y")) {
                    configuring = false;
                }
            }

            System.out.println(UtilsUI.BOLD + "\nConfiguration Summary:" + UtilsUI.RESET);

            Map<Model, List<Drone>> groupedByModel = new LinkedHashMap<>();
            for (ShowConfigurationEntry entry : configBuilder.showConfiguration()) {
                groupedByModel
                        .computeIfAbsent(entry.model(), k -> new ArrayList<>())
                        .add(entry.drone());
            }

            for (Map.Entry<Model, List<Drone>> entry : groupedByModel.entrySet()) {
                Model model = entry.getKey();
                List<Drone> drones = entry.getValue();
                System.out.printf(" - Model: %s, Quantity: %d%n", model.identity(), drones.size());
                for (Drone drone : drones) {
                    System.out.println("    • Drone SerialNumber: " + drone.identity());
                }
            }

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

    @Override
    public String headline() {
        return "Add a Drone to the Inventory";
    }

    private ShowProposal selectProposal() {
        Iterable<ShowProposal> showProposals = controller.getShowProposalList();

        if (showProposals == null || !showProposals.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo show proposal available." + UtilsUI.RESET);
            return null;
        }

        List<ShowProposal> showProposalList = new ArrayList<>();
        showProposals.forEach(showProposalList::add);

        ListWidget<ShowProposal> showProposalListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\nChoose a Show Proposal: \n" +
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
