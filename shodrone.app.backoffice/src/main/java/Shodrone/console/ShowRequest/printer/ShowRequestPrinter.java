package Shodrone.console.ShowRequest.printer;

import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.visitor.Visitor;

public class ShowRequestPrinter implements Visitor<ShowRequest> {
    @Override
    public void visit(ShowRequest visitee) {
        System.out.printf("Show -> %s\nStatus - %s\n", visitee.getShowRequestID().toString(), visitee.getShowRequestStatus());
    }
}
