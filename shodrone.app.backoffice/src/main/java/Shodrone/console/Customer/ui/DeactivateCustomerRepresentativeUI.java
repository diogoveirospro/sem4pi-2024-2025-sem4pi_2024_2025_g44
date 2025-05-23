package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.Customer.printer.CustomerRepresentativePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.application.DeactivateCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class DeactivateCustomerRepresentativeUI extends AbstractFancyListUI<CustomerRepresentative> {

    private final DeactivateCustomerRepresentativeController controller = new DeactivateCustomerRepresentativeController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CustomerPrinter printer = new CustomerPrinter();
    private Customer customer;

    @Override
    protected boolean doShow() {
        try {
            if (!authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Access denied." + UtilsUI.RESET);
                return false;
            }

            Iterable<CustomerRepresentative> reps = elements();
            if (!reps.iterator().hasNext()) {
                System.out.println(emptyMessage());
                UtilsUI.goBackAndWait();
                return true;
            }

            List<CustomerRepresentative> repList = new ArrayList<>();
            int index = 1;
            System.out.println(listHeader());
            for (CustomerRepresentative rep : reps) {
                System.out.printf("%-5d | ", index++);
                elementPrinter().visit(rep);
                repList.add(rep);
            }

            int option;
            do {
                option = UtilsUI.selectsIndex(repList);
                if (option == -2) return false;
                if (option < 0 || option >= repList.size()) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
                } else break;
            } while (true);

            CustomerRepresentative selectedRep = repList.get(option);

            controller.deactivateCustomerRepresentative(customer, selectedRep);
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nCustomer Representative deactivated successfully!" + UtilsUI.RESET);

            UtilsUI.goBackAndWait();
            return true;
        } catch (UserCancelledException e) {
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Error: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Deactivate Customer Representative";
    }

    private Customer selectCustomer() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        int option;
        do {
            ListWidget<Customer> customerListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "Choose a Customer:\n" + UtilsUI.RESET, customerList, printer);
            customerListWidget.show();
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                throw new UserCancelledException("User cancelled the operation.");
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return customerList.get(option);
            }
        } while (true);
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD + UtilsUI.BLUE +
                "\n\nChoose a Customer Representative to Deactivate:\n" + UtilsUI.RESET + UtilsUI.BOLD
                + String.format("%n%-5s | %-20s | %-30s | %-30s | %-20s |", "INDEX", "NAME", "POSITION", "EMAIL", "PHONE")
                + "\n"
                + String.format("%-5s-+-%-20s-+-%-30s-+-%-30s-+-%-20s-+", "-".repeat(5), "-".repeat(20),
                "-".repeat(30), "-".repeat(30), "-".repeat(20))
                + UtilsUI.RESET;
    }

    @Override
    protected Iterable<CustomerRepresentative> elements() {
        customer = selectCustomer();

        if (customer == null) {
            return null;
        }

        return controller.listRepresentativesOfCustomer(customer);
    }

    @Override
    protected CustomerRepresentativePrinter elementPrinter() {
        return new CustomerRepresentativePrinter();
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "No active customer representatives found for this customer." + UtilsUI.RESET;
    }
}
