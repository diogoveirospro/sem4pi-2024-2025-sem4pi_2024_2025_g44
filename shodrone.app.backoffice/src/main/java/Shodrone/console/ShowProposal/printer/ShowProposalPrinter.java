package Shodrone.console.ShowProposal.printer;

import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.visitor.Visitor;

public class ShowProposalPrinter implements Visitor<ShowProposal> {
    @Override
    public void visit(ShowProposal visitee) {
        System.out.printf("\nProposal Identifier: %s\nNumber of drones: %s \nDate of the show: %s \nTime of the show: %s \nStatus - %s \n",visitee.identity().proposalNumber(), visitee.quantityOfDrones(),visitee.dateOfShow(),visitee.timeOfShow(),visitee.status());
    }
}
