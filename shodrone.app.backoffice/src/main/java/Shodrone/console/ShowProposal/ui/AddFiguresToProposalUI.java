package Shodrone.console.ShowProposal.ui;

import Shodrone.console.Figure.printer.FiguresPrinter;
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

                ShowConfiguration configuration = proposal.configuration();
                if (configuration == null || configuration.droneModels() == null || configuration.droneModels().isEmpty()) {
                    System.out.println(UtilsUI.RED + "Error: The proposal does not have a valid configuration or drone models." + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return false;
                }

                Customer customer = proposal.customer();
                List<Figure> selectedFigures = new ArrayList<>();
                boolean configuring = true;

                while (configuring) {
                    System.out.println(UtilsUI.CYAN + "\nAdd a new figure to the proposal:" + UtilsUI.RESET);

                    Figure figure = selectFigure(customer);
                    if (figure == null) continue;

                    // Print models in the proposal
                    System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nModels in the Proposal:" + UtilsUI.RESET);
                    configuration.droneModels().forEach(model ->
                            System.out.println(UtilsUI.GREEN + "- " + model.identity().value() + UtilsUI.RESET)
                    );

                    // Print droneType of the figure
                    System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nDroneType of the Figure:" + UtilsUI.RESET);
                    System.out.println(UtilsUI.GREEN + figure.DSLDescription().requiredDroneTypes() + UtilsUI.RESET);

                    associateDroneTypesWithModels(figure, proposal);
                    selectedFigures.add(figure);
                    System.out.println(UtilsUI.GREEN + "\nFigure selected successfully!" + UtilsUI.RESET);

                    configuring = UtilsUI.confirm("Add another figure? (Y/N): ");
                }

                if (selectedFigures.isEmpty()) {
                    System.out.println(UtilsUI.YELLOW + "\nNo figures selected. Operation cancelled." + UtilsUI.RESET);
                    return false;
                }

                System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nYou have selected the following figures to add to the proposal:" + UtilsUI.RESET);
                for (Figure fig : selectedFigures) {
                    System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + fig.identity().code().toString() + UtilsUI.RESET);
                }

                if (!UtilsUI.confirm("\nConfirm adding the selected figures to the proposal? (Y/N): ")) {
                    System.out.println(UtilsUI.YELLOW + "\nOperation cancelled." + UtilsUI.RESET);
                    return false;
                }

                // Pass figures to the controller
                boolean success = controller.addFiguresToProposal(proposal, selectedFigures);
                if (success) {
                    System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nFigures and Drones added to the proposal successfully!" + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return true;
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n\nFailed to add Figures and Drones to the proposal." + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return false;
                }
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nYou do not have permission to perform this action." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
        } catch (UserCancelledException e) {
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.BOLD + UtilsUI.RED + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private void associateDroneTypesWithModels(Figure figure, ShowProposal proposal) {
        try {
            ShowConfiguration configuration = proposal.configuration();
            if (configuration == null || configuration.droneModels() == null || configuration.droneModels().isEmpty()) {
                throw new UserCancelledException("No drones available in the configuration.");
            }

            Set<String> droneTypes = figure.DSLDescription().requiredDroneTypes();

            if (droneTypes.isEmpty()) {
                System.out.println(UtilsUI.YELLOW + "No drone types to associate for this figure." + UtilsUI.RESET);
                return;
            }

            List<Model> availableModels = new ArrayList<>(configuration.droneModels());

            System.out.println(UtilsUI.CYAN + "\nAssociating all drone types with all models in the proposal..." + UtilsUI.RESET);
            for (String droneType : droneTypes) {
                for (Model model : availableModels) {
                    System.out.println(UtilsUI.GREEN + "Associated " + droneType + " with " + model.identity().value() + UtilsUI.RESET);
                }
            }
        } catch (NullPointerException e) {
            System.out.println(UtilsUI.RED + "Warning: An error occurred while accessing the show configuration. Returning to the main menu." + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
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

        ListWidget<Figure> figureListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Figure: \n" +
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

    @Override
    public String headline() {
        return "Add Figures to Proposal";
    }
}