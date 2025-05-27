package Shodrone.console.ShowProposal.printer;

import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.visitor.Visitor;

public class ShowProposalPrinter implements Visitor<ShowProposal> {
    @Override
    public void visit(ShowProposal visitee) {
        System.out.printf("\nNumber of drones: %s \nDate of the show: %s \nTime of the show: %s \nStatus - %s \n", visitee.quantityOfDrones(),visitee.dateOfShow(),visitee.timeOfShow(),visitee.status());
    }
}
