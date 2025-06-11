package Shodrone.console.ShowProposal.printer;

import eapli.framework.visitor.Visitor;

public class TemplatePrinter implements Visitor<String> {
    @Override
    public void visit(String visitee) {
        System.out.printf("%s", visitee);
    }
}
