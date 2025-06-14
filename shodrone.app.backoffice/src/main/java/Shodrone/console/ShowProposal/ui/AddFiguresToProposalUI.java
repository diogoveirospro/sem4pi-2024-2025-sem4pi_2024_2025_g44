package Shodrone.console.ShowProposal.ui;

import Shodrone.console.ShowProposal.printer.FiguresPrinter;
import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.domain.Entities.Customer;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;
import core.ShowProposal.application.AddFiguresToProposalController;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddFiguresToProposalUI extends AbstractFancyUI {

    private final AddFiguresToProposalController controller = new AddFiguresToProposalController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                ShowProposal proposal = selectProposal();
                if (proposal == null) {
                    System.out.println(UtilsUI.YELLOW + "No proposal selected. Operation cancelled." + UtilsUI.RESET);
                    return false;
                }

                Customer customer = proposal.customer();
                List<Figure> selectedFigures = new ArrayList<>();
                boolean configuring = true;

                while (configuring) {

                    Figure figure = selectFigure(customer);
                    if (figure == null) continue;

                    associateDroneTypesWithModels(figure, proposal);
                    selectedFigures.add(figure);
                    System.out.println(UtilsUI.GREEN + "Figure selected successfully!" + UtilsUI.RESET);

                    configuring = UtilsUI.confirm("Add another figure? (Y/N): ");
                }

                if (selectedFigures.isEmpty()) {
                    System.out.println(UtilsUI.YELLOW + "No figures selected. Operation cancelled." + UtilsUI.RESET);
                    return false;
                }

                System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nYou have selected the following figures to add to the proposal:" + UtilsUI.RESET);
                for (Figure fig : selectedFigures) {
                    System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + fig.toString() + UtilsUI.RESET);
                }

                if (!UtilsUI.confirm("\nConfirm adding the selected figures to the proposal? (Y/N): ")) {
                    System.out.println(UtilsUI.YELLOW + "Operation cancelled." + UtilsUI.RESET);
                    return false;
                }

                // Pass figures to the controller
                boolean success = controller.addFiguresToProposal(proposal, selectedFigures);
                if (success) {
                    System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nFigures and drones added to the proposal successfully!" + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return true;
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to add figures and drones to the proposal." + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return false;
                }
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nYou do not have permission to perform this action." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.BOLD + UtilsUI.RED + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private ShowProposal selectProposal() {
        Iterable<ShowProposal> proposals = controller.getShowProposalList();

        if (proposals == null || !proposals.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo proposals available." + UtilsUI.RESET);
            return null;
        }

        List<ShowProposal> proposalList = new ArrayList<>();
        proposals.forEach(proposalList::add);

        ListWidget<ShowProposal> proposalListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\nChoose a Proposal: \n" +
                UtilsUI.RESET, proposalList, new ShowProposalPrinter());
        proposalListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(proposalList);

            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return proposalList.get(option);
            }
        } while (true);
    }

    private Figure selectFigure(Customer customer) {
        Iterable<Figure> figures = controller.getFigureList(customer);

        if (figures == null || !figures.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo figures available." + UtilsUI.RESET);
            return null;
        }

        List<Figure> figureList = new ArrayList<>();
        figures.forEach(figureList::add);

        ListWidget<Figure> figureListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\nChoose a Figure: \n" +
                UtilsUI.RESET, figureList, new FiguresPrinter());
        figureListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(figureList);

            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return figureList.get(option);
            }
        } while (true);
    }

    private void associateDroneTypesWithModels(Figure figure, ShowProposal proposal) {
        try {
            ShowConfiguration configuration = proposal.configuration();
            if (configuration == null || configuration.droneModels() == null || configuration.droneModels().isEmpty()) {
                throw new IllegalArgumentException("No drones available in the configuration.");
            }

            Set<String> droneTypes = figure.DSLDescription().requiredDroneTypes();

            if (droneTypes.isEmpty()) {
                System.out.println(UtilsUI.YELLOW + "No drone types to associate for this figure." + UtilsUI.RESET);
                return;
            }

            List<Model> availableModels = new ArrayList<>(configuration.droneModels());

            for (String droneType : droneTypes) {
                System.out.println(UtilsUI.CYAN + "\nSelect a drone model for the drone type: " + droneType + UtilsUI.RESET);

                for (int i = 0; i < availableModels.size(); i++) {
                    Model model = availableModels.get(i);
                    System.out.println(UtilsUI.CYAN + (i + 1) + ". Model Name: " + model.identity().value() + UtilsUI.RESET);
                }

                int option;
                do {
                    option = UtilsUI.selectsIndex(availableModels);

                    if (option == -2) {
                        throw new UserCancelledException(UtilsUI.YELLOW + "Action cancelled by user." + UtilsUI.RESET);
                    }
                    if (option == -1) {
                        System.out.println(UtilsUI.RED + "Invalid option. Please try again." + UtilsUI.RESET);
                    } else {
                        Model selectedModel = availableModels.get(option);
                        System.out.println(UtilsUI.GREEN + "Associated " + droneType + " with " + selectedModel.identity().value() + UtilsUI.RESET);
                        break;
                    }
                } while (true);
            }
        } catch (NullPointerException e) {
            System.out.println(UtilsUI.RED + "Warning: An error occurred while accessing the show configuration. Returning to the main menu." + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + "Error: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
        }
    }

    @Override
    public String headline() {
        return "Add Figures to Proposal";
    }
}