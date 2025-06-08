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
            ShowProposal showProposal = selectProposal();
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
        Iterable<ShowProposal> showProposals = controller.getShowProposalCheckedList();

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


}