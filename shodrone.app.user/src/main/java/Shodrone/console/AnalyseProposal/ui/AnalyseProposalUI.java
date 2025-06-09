package Shodrone.console.AnalyseProposal.ui;

import Shodrone.DTO.ShowProposalDTO;
import Shodrone.console.AnalyseProposal.controller.AnalyseProposalController;
import Shodrone.console.SendFeedbackProposal.controller.SendFeedbackProposalController;
import Shodrone.console.SendFeedbackProposal.printer.ProposalPrinter;
import Shodrone.exceptions.FailedRequestException;
import Shodrone.exceptions.UserCancelledException;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User Interface for sending feedback on a show proposal.
 * This class allows users to select a proposal, choose a decision (accept or reject),
 * and provide feedback on the proposal.
 */
public class AnalyseProposalUI extends AbstractFancyUI {

    /**
     * Controller for handling the logic of sending feedback on a show proposal.
     * This controller interacts with the backend to perform the necessary operations.
     */
    private final AnalyseProposalController controller = new AnalyseProposalController();

    /**
     * doShow method to display the UI for sending feedback on a show proposal.
     * @return boolean indicating success or failure of the operation.
     */
    @Override
    protected boolean doShow() {
        try {
            ShowProposalDTO selectedProposal = showProposalAndSelect();

            ProposalDeliveryInfoCode deliveryInfo = controller.getProposalDeliveryInfoCode(selectedProposal);

            if (deliveryInfo != null) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nFeedback successfully retrieved!\n" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailure to retrieve feedback information!\n" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }

        } catch (UserCancelledException e) {
            return false;

        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);

        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
        }

        UtilsUI.goBackAndWait();
        return false;
    }
    /**
     * Method to display the list of show proposals and allow the user to select one.
     * @return ShowProposalDTO representing the selected proposal.
     * @throws FailedRequestException if the request to list proposals fails.
     * @throws IOException if there is an error reading from the console.
     */
    public ShowProposalDTO showProposalAndSelect() throws FailedRequestException, IOException {
        Iterable<ShowProposalDTO> proposals = controller.listProposals();
        if (proposals == null || !proposals.iterator().hasNext()) {
            throw new IllegalArgumentException("No Show Proposals available.");
        }

        List<ShowProposalDTO> proposalList = new ArrayList<>();
        proposals.forEach(proposalList::add);

        ProposalPrinter proposalPrinter = new ProposalPrinter();

        ListWidget<ShowProposalDTO> proposalListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Show Proposal:\n" +
                UtilsUI.RESET, proposalList, proposalPrinter);
        proposalListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(proposalList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option > proposalList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                ShowProposalDTO selected = proposalList.get(option);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nSelected Show Proposal: " + selected.toString()  + "\n"
                        + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }

    @Override
    public String headline() {
        return "Send Feedback Proposal";
    }
}
