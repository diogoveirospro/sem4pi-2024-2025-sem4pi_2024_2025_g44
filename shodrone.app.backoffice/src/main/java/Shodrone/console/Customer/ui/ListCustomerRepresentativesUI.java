package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.Customer.printer.CustomerRepresentativePrinter;
import core.Customer.application.ListCustomerRepresentativesController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerRepresentativesUI extends AbstractListUI<CustomerRepresentative> {

    ListCustomerRepresentativesController controller = new ListCustomerRepresentativesController();

    @Override
    protected Iterable<CustomerRepresentative> elements() {
        Customer customer = selectCustomer();
        return this.controller.listRepresentativesOfCustomer(customer);
    }

    @Override
    protected Visitor<CustomerRepresentative> elementPrinter() {
        return new CustomerRepresentativePrinter();
    }

    @Override
    protected String elementName() {
        return "Customer Representative";
    }

    @Override
    protected String listHeader() {
        return "List of Customer Representatives";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "List Customer Representatives";
    }

    private Customer selectCustomer() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println("No customers available.");
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : customers) {
            customerList.add(customer);
        }

        ListWidget<Customer> customerListWidget = new ListWidget<>("Customers", customers, new CustomerPrinter());
        customerListWidget.show();

        int option = UtilsUI.selectsIndex(customerList);
        if (option == -2) {
            return null;
        }

        return customerList.get(option - 1);
    }
}
