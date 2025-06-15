package Shodrone.console.AnalyseProposal.printer;

import Shodrone.DTO.ShowProposalDTO;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class ProposalPrinter implements Visitor<ShowProposalDTO> {

    @Override
    public void visit(ShowProposalDTO visitee) {
        System.out.printf(
                UtilsUI.BOLD + "\nProposal Number: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Date: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Time: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Duration: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Location: " + UtilsUI.RESET + "%s\n",
                visitee.proposalNumber,
                visitee.dateOfProposal,
                visitee.timeOfProposal,
                visitee.showDuration,
                visitee.showLocation
        );
    }
}