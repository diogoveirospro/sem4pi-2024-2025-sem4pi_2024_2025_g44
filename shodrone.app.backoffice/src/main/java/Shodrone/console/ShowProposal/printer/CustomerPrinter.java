package Shodrone.console.ShowProposal.printer;

import core.Customer.domain.Entities.Customer;
import eapli.framework.visitor.Visitor;

public class CustomerPrinter implements Visitor<Customer> {
    @Override
    public void visit(Customer visitee) {
        System.out.printf("%s - %s", visitee.name(), visitee.vat());
    }
}
