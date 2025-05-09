package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import core.Customer.application.AddCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User Interface for adding a customer representative.
 * This class extends AbstractFancyUI and implements the doShow method to display the UI.
 */
public class AddCustomerRepresentativeUI extends AbstractFancyUI {

    private final AddCustomerRepresentativeController controller = new AddCustomerRepresentativeController();
    private final CustomerPrinter printer = new CustomerPrinter();
    private final UserManagementService userSvc = AuthzRegistry.userService();


    @Override
    protected boolean doShow() {
        try {
            Customer customer = selectCustomer();
            if (customer == null) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customer selected. Operation canceled." + UtilsUI.RESET);
                return false;
            }

            Name name = enterValidName();
            Email email = enterValidEmail();
            PhoneNumber phoneNumber = enterValidPhoneNumber();
            Position position = enterValidPosition();

            CustomerRepresentative representative = new CustomerRepresentative(name, email, phoneNumber, position, customer);

            String[] credentials = enterUsernameAndPassword();
            String username = credentials[0];
            String password = credentials[1];

            addCustomerRepresentative(representative, customer, username, password, phoneNumber);
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Customer Representative added successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Add Customer Representative";
    }

    private void addCustomerRepresentative(CustomerRepresentative representative, Customer customer, String username, String password, PhoneNumber phoneNumber) {
        try {
            controller.addCustomerRepresentative(representative, customer, username, password, phoneNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
        }
    }

    private String[] enterUsernameAndPassword() {
        String username;
        String passwordChars;
        String password;
        ShodronePasswordPolicy passwordPolicy = new ShodronePasswordPolicy();

        do {
            username = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's username: " + UtilsUI.RESET);

            java.io.Console console = System.console();
            if (console != null) {
                passwordChars = UtilsUI.readPassword(UtilsUI.BOLD + "Enter the representative's password (at least 8 characters, including a number): " + UtilsUI.RESET);
                password = passwordChars;
            } else {
                // Fallback in case console is not available
                password = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's password (at least 8 characters, including a number): " + UtilsUI.RESET);
            }

            Iterable<SystemUser> allUsers = userSvc.allUsers();
            boolean found = checkIfUsernameIsInUse(allUsers, username, false);

            if (Objects.requireNonNull(username).isEmpty() || Objects.requireNonNull(password).isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Username and/or password cannot be empty. Please try again." + UtilsUI.RESET);
            } else if (found) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Username already exists. Please choose a different one." + UtilsUI.RESET);
            } else if (!passwordPolicy.isSatisfiedBy(password)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password does not meet the required criteria. Please try again." + UtilsUI.RESET);
            } else {
                break;
            }
        } while (true);

        return new String[]{username, password};
    }

    private static boolean checkIfUsernameIsInUse(Iterable<SystemUser> allUsers, String username, boolean found) {
        for (SystemUser user : allUsers) {
            if (user.username().equals(Username.valueOf(username))) {
                found = true;
                break;
            }
        }
        return found;
    }

    private Customer selectCustomer() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        ListWidget<Customer> customerListWidget = new ListWidget<>("Choose a Customer", customerList, printer);
        customerListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
                return null;
            }

            if (option < 1 || option > customerList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return customerList.get(option - 1);
            }
        } while (true);
    }

    private Name enterValidName() {
        String name;
        do {
            try {
                name = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's name: " + UtilsUI.RESET);
                return new Name(name);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid name. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private Email enterValidEmail() {
        String email;
        do {
            try {
                email = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's email: " + UtilsUI.RESET);
                return new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid email. Please try again." + UtilsUI.RESET);
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
                if (country == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No country selected. Operation canceled." + UtilsUI.RESET);
                    return null;
                }
                countryCode = controller.countryCode(country);
                phoneNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's phone number: " + UtilsUI.RESET);
                return new PhoneNumber(countryCode, phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid phone number. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private String selectCountry() {
        List<String> countries = controller.availableCountries();
        if (countries == null || countries.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No countries available." + UtilsUI.RESET);
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
                position = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's position: " + UtilsUI.RESET);
                return new Position(position);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid position. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }
}