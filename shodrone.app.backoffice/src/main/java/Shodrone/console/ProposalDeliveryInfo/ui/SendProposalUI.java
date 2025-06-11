package Shodrone.console.ProposalDeliveryInfo.ui;

import Shodrone.console.ShowProposal.printer.AnotherShowProposalPrinter;
import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.Persistence.PersistenceContext;
import core.ProposalDeliveryInfo.application.SendProposalController;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.Shared.domain.ValueObjects.Email;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * UI for sending a proposal to a customer.
 */
public class SendProposalUI extends AbstractFancyUI {

    /**
     * Controller for sending proposals.
     */
    private final SendProposalController controller = new SendProposalController();

    /**
     * Authorization service to check user permissions.
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Repository for CRM collaborators.
     */
    private final CRMCollaboratorRepository crmCollaboratorRepository = PersistenceContext.repositories().crmCollaborators();

    /**
     * doShow method to display the UI and handle user interactions for sending a proposal.
     * @return true if the proposal was successfully sent, false otherwise.
     */
    @Override
    protected boolean doShow() {
        boolean success;

        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)){

                ShowProposal selectedProposal = showProposalAndSelect();
                CRMCollaborator crmCollaborator = null;

                if (authz.session().isPresent()) {
                    Email email = new Email(authz.session().get().authenticatedUser().email().toString());
                    crmCollaborator = crmCollaboratorRepository.findByEmail(email);
                }

                ProposalDeliveryInfoCode code = controller.sendProposal(selectedProposal, crmCollaborator);

                success = code != null;

                if (success){
                    System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nProposal Successfully Sent to the Customer!\n"
                            + UtilsUI.RESET);
                    System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "-> Please Provide the Code '" + code + "' " +
                            "to the Customer '" + selectedProposal.request().customer().name().toString() + "' for Downloading." + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return true;
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to Send the Proposal to the Customer!\n" + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return false;
                }

            }

            return false;

        } catch (UserCancelledException e) {
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    /**
     * Returns the headline for the UI.
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "Send Show Proposal to Customer";
    }

    /**
     * Displays a list of ready-to-send proposals and allows the user to select one.
     * @return the selected ShowProposal.
     * @throws IllegalArgumentException if no proposals are available.
     */
    public ShowProposal showProposalAndSelect() {
        Iterable<ShowProposal> proposals = controller.listReadyToSendProposals();
        if (proposals == null || !proposals.iterator().hasNext()) {
            throw new IllegalArgumentException("No Show Proposals available.");
        }

        List<ShowProposal> proposalList = new ArrayList<>();
        proposals.forEach(proposalList::add);

        AnotherShowProposalPrinter proposalPrinter = new AnotherShowProposalPrinter();

        ListWidget<ShowProposal> proposalListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Show Proposal:\n" +
                UtilsUI.RESET, proposalList, proposalPrinter);
        proposalListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(proposalList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option > proposalList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                ShowProposal selected = proposalList.get(option);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nSelected Show Proposal: " +
                        selected.identity().proposalNumber()  + "\n" + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }
}
