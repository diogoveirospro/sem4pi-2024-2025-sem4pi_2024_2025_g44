package Shodrone.console.SendFeedbackProposal.printer;

import Shodrone.DTO.ShowProposalDTO;
import eapli.framework.visitor.Visitor;

public class ProposalPrinter implements Visitor<ShowProposalDTO> {

    @Override
    public void visit(ShowProposalDTO visitee) {
        System.out.printf(
                "\nShow Proposal number: %s\nDate: %s\nTime: %s\nDuration: %s\nLocation: %s\n",
                visitee.proposalNumber,
                visitee.dateOfProposal,
                visitee.timeOfProposal,
                visitee.showDuration,
                visitee.showLocation
        );
    }
}
