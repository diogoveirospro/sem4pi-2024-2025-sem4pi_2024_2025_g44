package Shodrone.console.ShowProposal.printer;

import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class ShowProposalPrinter implements Visitor<ShowProposal> {
    @Override
    public void visit(ShowProposal visitee) {
        System.out.printf(
                UtilsUI.BOLD + "\nProposal Number: " + UtilsUI.RESET + "%s" +
                UtilsUI.BOLD + "\nNumber of Drones: " + UtilsUI.RESET + "%s " +
                UtilsUI.BOLD + "\nDate: " + UtilsUI.RESET + "%s " +
                UtilsUI.BOLD + "\nTime: " + UtilsUI.RESET + "%s \n",
                visitee.identity().proposalNumber(),
                visitee.quantityOfDrones(),
                visitee.dateOfShow(),
                visitee.timeOfShow(),
                visitee.status());
    }
}
