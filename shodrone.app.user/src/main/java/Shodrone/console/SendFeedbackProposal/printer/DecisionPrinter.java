package Shodrone.console.SendFeedbackProposal.printer;

import eapli.framework.visitor.Visitor;

public class DecisionPrinter implements Visitor<String> {
    @Override
    public void visit(String visitee) {
        System.out.printf("%s", visitee);
    }
}
