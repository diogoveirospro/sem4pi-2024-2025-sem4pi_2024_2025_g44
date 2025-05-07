package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.Customer.printer.CustomerRepresentativePrinter;
import core.Customer.application.DeactivateCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class DeactivateCustomerRepresentativeUI extends AbstractFancyUI {
    //private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DeactivateCustomerRepresentativeController controller = new DeactivateCustomerRepresentativeController();
    @Override
    protected boolean doShow() {
        //authz.ensureAuthenticatedUserHasAnyOf(Role.valueOf("CRMCOLLABORATOR"));
        Customer customer = selectCustomer();
        if (customer == null) {
            return false;
        }
        CustomerRepresentative representative = selectCustomerRepresentative(customer);
        if (representative == null) {
            return false;
        }

        controller.deactivateCustomerRepresentative(customer, representative);

        return true;
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

    private CustomerRepresentative selectCustomerRepresentative(Customer customer) {
        Iterable<CustomerRepresentative> representatives = controller.listRepresentativesOfCustomer(customer);
        if (representatives == null || !representatives.iterator().hasNext()) {
            System.out.println("No customer representatives available.");
            return null;
        }
        List<CustomerRepresentative> representativeList = new ArrayList<>();
        for (CustomerRepresentative representative : representatives) {
            representativeList.add(representative);
        }
        ListWidget<CustomerRepresentative> representativeListWidget = new ListWidget<>("Customer Representatives", representatives, new CustomerRepresentativePrinter());
        representativeListWidget.show();
        int option = UtilsUI.selectsIndex(representativeList);
        if (option == -2) {
            return null;
        }
        return representativeList.get(option - 1);
    }

    @Override
    public String headline() {
        return UtilsUI.generateHeader(UtilsUI.PURPLE, "Deactivate Customer Representative");
    }
}
