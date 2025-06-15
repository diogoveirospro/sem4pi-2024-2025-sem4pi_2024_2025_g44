package Shodrone.console.ShowProposal.ui;

import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.ModelOfDrone.domain.Entities.Model;
import core.ShowProposal.application.AccShowPropController;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.*;

public class AccShowPropUI extends AbstractFancyUI {

    private final AccShowPropController controller = new AccShowPropController();

    @Override
    protected boolean doShow() {
        try {
            ShowProposal showProposal = showProposalAndSelect();
            if (showProposal == null) return false;
            boolean success = controller.acceptProp(showProposal);
            if (success) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nShow proposal accepted successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to accept show proposal." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
        } catch (UserCancelledException e) {
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
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
        return "Accept a Show Proposal";
    }

    /**
     * Prompts the user to select a model from the list of available drone models.
     *
     * @return the selected {@link Model} or null if none are available.
     * @throws UserCancelledException if the user cancels the action.
     */
    public ShowProposal showProposalAndSelect() {
        Iterable<ShowProposal> proposals = controller.getShowProposalCheckedList();
        if (proposals == null || !proposals.iterator().hasNext()) {
            throw new IllegalArgumentException("No Show Proposals available.");
        }

        List<ShowProposal> proposalList = new ArrayList<>();
        proposals.forEach(proposalList::add);

        ShowProposalPrinter proposalPrinter = new ShowProposalPrinter();

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
                        selected.identity().proposalNumber() + "\n" + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }


}