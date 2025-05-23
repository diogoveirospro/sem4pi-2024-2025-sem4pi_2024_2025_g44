package Shodrone.console.Customer.printer;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import eapli.framework.visitor.Visitor;

public class CustomerRepresentativePrinter implements Visitor<CustomerRepresentative> {


    @Override
    public void visit(CustomerRepresentative visitee) {

        System.out.printf("%-20s | %-30s | %-30s | %-20s |\n",
                visitee.name().toString(),
                visitee.position().toString(),
                visitee.email().toString(),
                visitee.phoneNumber().toString());
    }
}
