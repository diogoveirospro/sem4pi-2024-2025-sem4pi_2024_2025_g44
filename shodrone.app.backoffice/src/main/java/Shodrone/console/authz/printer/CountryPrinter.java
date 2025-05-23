package Shodrone.console.authz.printer;

import eapli.framework.visitor.Visitor;

public class CountryPrinter implements Visitor<String> {
    @Override
    public void visit(String visitee) {
        System.out.printf("%s", visitee);
    }
}
