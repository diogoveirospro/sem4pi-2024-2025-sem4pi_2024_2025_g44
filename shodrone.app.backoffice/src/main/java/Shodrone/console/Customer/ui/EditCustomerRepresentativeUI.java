package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.Customer.printer.CustomerRepresentativePrinter;
import core.Customer.application.EditCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class EditCustomerRepresentativeUI extends AbstractFancyUI {

    //private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EditCustomerRepresentativeController controller = new EditCustomerRepresentativeController();

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

        Email newEmail = enterValidEmail();
        PhoneNumber newPhone = enterValidPhoneNumber();

        controller.changeCustomerRepresentativeInfo(customer, representative, newEmail, newPhone);
        return true;
    }

    @Override
    public String headline() {
        return UtilsUI.generateHeader(UtilsUI.PURPLE, "Edit Customer Representative Information");
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

    private PhoneNumber enterValidPhoneNumber() {
        String phoneNumber;
        String country;
        String countryCode;
        do {
            try {
                country = selectCountry();
                countryCode = controller.countryCode(country);
                phoneNumber = UtilsUI.readLineFromConsole("Enter the new representative's phone number: ");
                return new PhoneNumber(countryCode,phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid phone number. Please try again.");
            }
        } while (true);
    }

    private String selectCountry() {
        List<String> countries = controller.availableCountries();
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries available.");
            return null;
        }

        ListWidget<String> countryListWidget = new ListWidget<>("Countries", countries, String::toString);
        countryListWidget.show();

        int option = UtilsUI.selectsIndex(countries);
        if (option == -2) {
            return null;
        }

        return countries.get(option - 1);
    }

    private Email enterValidEmail() {
        String email;
        do {
            try {
                email = UtilsUI.readLineFromConsole("Enter the new representative's email: ");
                return new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email. Please try again.");
            }
        } while (true);
    }
}
