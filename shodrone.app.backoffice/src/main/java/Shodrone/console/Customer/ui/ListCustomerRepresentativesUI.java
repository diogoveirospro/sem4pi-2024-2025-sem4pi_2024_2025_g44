package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.Customer.printer.CustomerRepresentativePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.application.ListCustomerRepresentativesController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * UI for listing all customer representatives.
 * This class is responsible for displaying the list of customer representatives.
 */
public class ListCustomerRepresentativesUI extends AbstractFancyListUI<CustomerRepresentative> {

    /**
     * Controller for listing customer representatives.
     */
    private final ListCustomerRepresentativesController controller = new ListCustomerRepresentativesController();
    private final CustomerPrinter printer = new CustomerPrinter();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Method to show the UI.
     * @return true if the UI was shown successfully
     */
    @Override
    public boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                super.doShow();
                UtilsUI.goBackAndWait();
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    /**
     * List all customer representatives for a selected customer.
     * @return Iterable of customer representatives
     */
    @Override
    protected Iterable<CustomerRepresentative> elements() {
        Customer customer = selectCustomer();
        if (customer == null) {
            return new ArrayList<>();
        }
        return controller.listRepresentativesOfCustomer(customer);
    }

    /**
     * Prints the details of each customer representative.
     * @return Visitor that prints the details of each representative
     */
    @Override
    protected Visitor<CustomerRepresentative> elementPrinter() {
        return new CustomerRepresentativePrinter();
    }

    /**
     * Returns the name of the element to be printed.
     * @return the name of the element
     */
    @Override
    protected String elementName() {
        return "Customer Representative";
    }

    /**
     * Returns the header for the list of customer representatives.
     * @return the header for the list
     */
    @Override
    protected String listHeader() {
        return "\nList of Customer Representatives\n";
    }

    /**
     * Returns the message to be displayed when there are no representatives.
     * @return the message to be displayed
     */
    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "No customer representatives available." + UtilsUI.RESET;
    }

    /**
     * Returns the title of the UI.
     * @return the title of the UI
     */
    @Override
    public String headline() {
        return "List Customer Representatives";
    }

    /**
     * Allows the user to select a customer.
     * @return the selected customer
     */
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
            ListWidget<Customer> customerListWidget = new ListWidget<>("Choose a Customer\n", customerList, printer);
            customerListWidget.show();
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled.\n" + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return customerList.get(option);
            }
        } while (true);
    }
}