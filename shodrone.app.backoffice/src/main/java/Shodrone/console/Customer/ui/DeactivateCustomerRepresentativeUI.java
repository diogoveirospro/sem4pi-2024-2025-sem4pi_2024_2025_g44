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
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * User Interface for deactivating a customer representative.
 * This class extends AbstractFancyUI and implements the doShow method to display the UI.
 */
public class DeactivateCustomerRepresentativeUI extends AbstractFancyUI {

    private final DeactivateCustomerRepresentativeController controller = new DeactivateCustomerRepresentativeController();
    private final CustomerPrinter printer = new CustomerPrinter();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                Customer customer = selectCustomer();
                if (customer == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customer selected. Operation canceled." + UtilsUI.RESET);
                    return false;
                }

                CustomerRepresentative representative = selectCustomerRepresentative(customer);
                if (representative == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No representative selected. Operation canceled." + UtilsUI.RESET);
                    return false;
                }

                controller.deactivateCustomerRepresentative(customer, representative);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Customer Representative deactivated successfully!" + UtilsUI.RESET);
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

    private CustomerRepresentative selectCustomerRepresentative(Customer customer) {
        Iterable<CustomerRepresentative> representatives = controller.listRepresentativesOfCustomer(customer);
        if (representatives == null || !representatives.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customer representatives available." + UtilsUI.RESET);
            return null;
        }

        List<CustomerRepresentative> representativeList = new ArrayList<>();
        representatives.forEach(representativeList::add);



        int option;
        do {
            ListWidget<CustomerRepresentative> representativeListWidget = new ListWidget<>("Choose a Representative", representativeList, new CustomerRepresentativePrinter());
            representativeListWidget.show();
            option = UtilsUI.selectsIndex(representativeList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled." + UtilsUI.RESET);
                return null;
            }

            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return representativeList.get(option);
            }
        } while (true);
    }
}