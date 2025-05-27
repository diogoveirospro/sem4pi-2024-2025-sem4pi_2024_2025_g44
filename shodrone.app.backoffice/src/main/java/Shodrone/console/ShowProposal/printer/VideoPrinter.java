package Shodrone.console.ShowProposal.printer;

import core.ShowProposal.domain.ValueObjects.Video;
import eapli.framework.visitor.Visitor;

public class VideoPrinter implements Visitor<Video> {
    @Override
    public void visit(Video visitee) {
        System.out.printf("Title: %s%nURL: %s%n", visitee.title(), visitee.url());
    }
}
