package Shodrone.console.ShowProposal.printer;

import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.visitor.Visitor;

public class ShowRequestPrinter implements Visitor<ShowRequest> {
    @Override
    public void visit(ShowRequest visitee) {
        System.out.printf("\nRequest Identifier: %s\nEmail collaborator: %s \nDate of the show: %s \nTime of the show: %s \nLocation of the show: %s\nStatus - %s \n",visitee.identity().showRequestID() ,visitee.crmCollaborator().getEmail(),visitee.dateOfShow(),visitee.timeOfShow(),visitee.location(), visitee.status());
    }
}
