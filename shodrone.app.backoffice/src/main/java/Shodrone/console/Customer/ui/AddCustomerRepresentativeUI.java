package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CountryPrinter;
import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.application.AddCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.ShodronePasswordPolicy;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
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
import java.util.regex.Pattern;

/**
 * User Interface for adding a customer representative.
 * This class extends AbstractFancyUI and implements the doShow method to display the UI.
 */
public class AddCustomerRepresentativeUI extends AbstractFancyUI {

    private final AddCustomerRepresentativeController controller = new AddCustomerRepresentativeController();
    private final CustomerPrinter printer = new CustomerPrinter();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CountryPrinter countryPrinter = new CountryPrinter();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                Customer customer = selectCustomer();
                if (customer == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customer selected. Operation canceled." + UtilsUI.RESET);
                    return false;
                }

                Name name = enterValidName();
                Email email = enterValidEmail();
                PhoneNumber phoneNumber = enterValidPhoneNumber();
                if (phoneNumber == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo phone number registered. Operation canceled." + UtilsUI.RESET);
                    return false;
                }
                Position position = enterValidPosition();

                CustomerRepresentative representative = new CustomerRepresentative(name, email, phoneNumber, position, customer);

                String[] credentials = enterUsernameAndPassword();
                String username = credentials[0];
                String password = credentials[1];
                addCustomerRepresentative(representative, customer, username, password, phoneNumber);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nCustomer Representative added successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (UserCancelledException e) {
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
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
            username = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the representative's username (or type 'cancel' to go back): " + UtilsUI.RESET);

            if (username == null || username.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nUsername cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if ("cancel".equalsIgnoreCase(username)) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            java.io.Console console = System.console();
            if (console != null) {
                passwordChars = UtilsUI.readPassword(UtilsUI.BOLD + "\n\nEnter the representative's password (or type 'cancel' to go back) (at least 6 characters, including a number): " + UtilsUI.RESET);
                password = passwordChars;
            } else {
                // Fallback in case console is not available
                password = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's password (or type 'cancel' to go back) (at least 6 characters, including a number): " + UtilsUI.RESET);
            }

            if (password == null || password.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nPassword cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if ("cancel".equalsIgnoreCase(password)) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            boolean found = controller.checkIfUsernameIsInUse(username);
            if (found) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nUsername already exists. Please choose a different one." + UtilsUI.RESET);
            } else if (!passwordPolicy.isSatisfiedBy(password)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nPassword does not meet the required criteria. Please try again." + UtilsUI.RESET);
            } else {
                break;
            }
        } while (true);

        return new String[]{username, password};
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

    private Name enterValidName() {
        String name;
        String nameRegex = "^[A-Za-zÀ-ÿ\\s]+$";
        do {
            try {
                name = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the representative's name (or type 'cancel' to go back): " + UtilsUI.RESET);
                assert name != null;
                if ("cancel".equalsIgnoreCase(name)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                if (name.isEmpty()){
                    throw new IllegalArgumentException("\nName cannot be empty.");
                }
                if (!Pattern.matches(nameRegex, name)) {
                    throw new IllegalArgumentException("\nName can only contain letters and spaces.");
                }
                return new Name(name);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    private Email enterValidEmail() {
        String email;

        do {
            try {
                email = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the representative's email (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(email)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                } else if (controller.checkIfEmailIsInUse(email)) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nEmail already exists. Please choose a different one." + UtilsUI.RESET);
                    continue;
                } else {
                    assert email != null;
                    if (email.isEmpty()) {
                        throw new IllegalArgumentException("\nEmail cannot be empty.");
                    }
                }
                return new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid email. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private PhoneNumber enterValidPhoneNumber() {
        String phoneNumber;
        String country;
        String countryCode;
        country = selectCountry();
        do {
            try {

                if (country == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo country selected. Operation canceled." + UtilsUI.RESET);
                    return null;
                }
                countryCode = controller.countryCode(country);
                phoneNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the representative's phone number (or type 'cancel' to go back) (No spaces): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(phoneNumber)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                return new PhoneNumber(countryCode, phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n" + e.getMessage() + "Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private String selectCountry() {
        List<String> countries = controller.availableCountries();
        if (countries == null || countries.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo countries available." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> countryListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                "\nChoose a Country: \n" + UtilsUI.RESET, countries, countryPrinter);
        countryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(countries);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled." + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else if (option < 0 || option >= countries.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo country selected! Please select a country!" + UtilsUI.RESET);
            } else {
                return countries.get(option);
            }
        } while (true);
    }

    private Position enterValidPosition() {
        String position;
        String positionRegex = "^[A-Za-zÀ-ÿ\\s]+$";
        do {
            try {
                position = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the representative's position (or type 'cancel' to go back): " + UtilsUI.RESET);
                assert position != null;
                if ("cancel".equalsIgnoreCase(position)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                if (position.isEmpty()){
                    throw new IllegalArgumentException("\nPosition cannot be empty.");
                }
                if (!Pattern.matches(positionRegex, position)) {
                    throw new IllegalArgumentException("\nPosition can only contain letters and spaces.");
                }
                return new Position(position);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + " Invalid position. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }
}