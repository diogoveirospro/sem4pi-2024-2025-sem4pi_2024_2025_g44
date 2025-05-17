package Shodrone.console.Customer.printer;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import eapli.framework.visitor.Visitor;

public class CustomerRepresentativePrinter implements Visitor<CustomerRepresentative> {


    @Override
    public void visit(CustomerRepresentative visitee) {
        System.out.printf("\nName: %s \nPosition: %s \nEmail: %s \nPhone number: %s\n", visitee.name(), visitee.position(), visitee.email(), visitee.phoneNumber());
    }
}
