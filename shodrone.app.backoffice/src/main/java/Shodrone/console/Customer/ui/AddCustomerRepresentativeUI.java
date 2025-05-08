package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import core.Customer.application.AddCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerRepresentativeUI extends AbstractFancyUI {

    //private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AddCustomerRepresentativeController controller = new AddCustomerRepresentativeController();

    @Override
    protected boolean doShow() {
        //authz.ensureAuthenticatedUserHasAnyOf(Role.valueOf("CRMCOLLABORATOR"));
        Customer customer = selectCustomer();
        if (customer == null) {
            System.out.println("No customer selected. Operation canceled.");
            return false;
        }

        Name name = enterValidName();
        Email email = enterValidEmail();
        PhoneNumber phoneNumber = enterValidPhoneNumber();
        Position position = enterValidPosition();

        CustomerRepresentative representative = new CustomerRepresentative(name, email, phoneNumber, position, customer);

        addCustomerRepresentative(representative, customer);
        return true;
    }

    @Override
    public String headline() {
        return "Add Customer Representative";
    }

    public void addCustomerRepresentative(CustomerRepresentative representative, Customer customer) {
        controller.addCustomerRepresentative(representative, customer);
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

    private Name enterValidName() {
        String name;
        do {
            try {
                name = UtilsUI.readLineFromConsole("Enter the representative's name: ");
                return new Name(name);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid name. Please try again.");
            }
        } while (true);
    }

    private Email enterValidEmail() {
        String email;
        do {
            try {
                email = UtilsUI.readLineFromConsole("Enter the representative's email: ");
                return new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email. Please try again.");
            }
        } while (true);
    }

    private PhoneNumber enterValidPhoneNumber() {
        String phoneNumber;
        String country;
        String countryCode;
        do {
            try {
                country = selectCountry();
                countryCode = controller.countryCode(country);
                phoneNumber = UtilsUI.readLineFromConsole("Enter the representative's phone number: ");
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

    private Position enterValidPosition() {
        String position;
        do {
            try {
                position = UtilsUI.readLineFromConsole("Enter the representative's position: ");
                return new Position(position);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid position. Please try again.");
            }
        } while (true);
    }
}