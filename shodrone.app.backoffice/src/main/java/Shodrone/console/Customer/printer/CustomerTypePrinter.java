package Shodrone.console.Customer.printer;

import core.Customer.domain.ValueObjects.CustomerType;
import eapli.framework.visitor.Visitor;

public class CustomerTypePrinter implements Visitor<String> {

    @Override
    public void visit(String visitee) {
        System.out.printf("%s", visitee);
    }
}
