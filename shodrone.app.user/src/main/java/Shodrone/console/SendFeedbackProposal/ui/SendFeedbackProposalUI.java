package Shodrone.console.SendFeedbackProposal.ui;

import Shodrone.DTO.ShowProposalDTO;
import Shodrone.console.SendFeedbackProposal.controller.SendFeedbackProposalController;
import Shodrone.console.SendFeedbackProposal.printer.DecisionPrinter;
import Shodrone.console.SendFeedbackProposal.printer.ProposalPrinter;
import Shodrone.exceptions.FailedRequestException;
import Shodrone.exceptions.UserCancelledException;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static shodrone.presentation.UtilsUI.readLineFromConsole;

/**
 * User Interface for sending feedback on a show proposal.
 * This class allows users to select a proposal, choose a decision (accept or reject),
 * and provide feedback on the proposal.
 */
public class SendFeedbackProposalUI extends AbstractFancyUI {

    /**
     * Controller for handling the logic of sending feedback on a show proposal.
     * This controller interacts with the backend to perform the necessary operations.
     */
    private final SendFeedbackProposalController controller = new SendFeedbackProposalController();

    /**
     * doShow method to display the UI for sending feedback on a show proposal.
     * @return boolean indicating success or failure of the operation.
     */
    @Override
    protected boolean doShow() {
        boolean success;

        try {
            ShowProposalDTO selectedProposal = showProposalAndSelect();
            String decision = chooseDecision();
            String feedback = enterFeedback();

            success = controller.sendFeedbackProposal(selectedProposal.proposalNumber, decision, feedback);

            if (success){
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nFeedback successfully provided!\n" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailure to Provide Feedback!!\n" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }

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
     * Method to choose a decision (accept or reject) for the selected proposal.
     * @return String representing the chosen decision.
     */
    private String chooseDecision() {

        List<String> decisionList = new ArrayList<>();
        decisionList.add("Accept Proposal");
        decisionList.add("Reject Proposal");

        ListWidget<String> templates = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nWhat is Your Decision?:\n" +
                UtilsUI.RESET, decisionList, new DecisionPrinter());
        templates.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(decisionList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option > decisionList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                String selected;

                if (option == 0){
                    selected = "ACCEPTED";
                } else if (option == 1){
                    selected = "REJECTED";
                } else {
                   throw new IllegalArgumentException("Invalid option selected.");
                }

                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nYour Decision: " + selected  + "\n"
                        + UtilsUI.RESET);
                return selected;
            }

        } while (true);
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
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nSelected Show Proposal: " + selected.proposalNumber()  + "\n"
                        + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }

    /**
     * Method to enter feedback for the selected proposal.
     * @return String representing the entered feedback.
     */
    private String enterFeedback() {
        do {
            try {
                if (UtilsUI.confirm(UtilsUI.BOLD + "Would You Like to Write Some Feedback? (Y/N):" + UtilsUI.RESET)) {
                    String feedback = readLineFromConsole(UtilsUI.BOLD + "\nEnter a Feedback (or type 'cancel' to go back): " + UtilsUI.RESET);

                    if ("cancel".equalsIgnoreCase(feedback)) {
                        throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                    } else if (feedback.isEmpty()){
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFeedback cannot be empty." + UtilsUI.RESET);
                        continue;
                    }

                    return feedback;

                } else {
                    return "Not Provided";
                }

            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    /**
     * Method to get the headline for the UI.
     * @return String representing the headline.
     */
    @Override
    public String headline() {
        return "Send Feedback Proposal";
    }
}
