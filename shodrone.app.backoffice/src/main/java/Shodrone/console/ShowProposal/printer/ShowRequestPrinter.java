package Shodrone.console.ShowProposal.printer;

import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class ShowRequestPrinter implements Visitor<ShowRequest> {
    @Override
    public void visit(ShowRequest visitee) {
        System.out.printf(UtilsUI.BOLD + "\nRequest Identifier: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Collaborator: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Date: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Time: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Location: " + UtilsUI.RESET + "%s\n",
                        visitee.identity().showRequestID(),
                        visitee.crmCollaborator().getEmail(),
                        visitee.dateOfShow(),
                        visitee.timeOfShow(),
                        visitee.location());
    }
}
