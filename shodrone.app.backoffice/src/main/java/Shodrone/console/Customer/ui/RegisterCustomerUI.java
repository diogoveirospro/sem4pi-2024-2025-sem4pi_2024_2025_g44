package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CountryPrinter;
import Shodrone.console.Customer.printer.CustomerTypePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.application.RegisterCustomerController;
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

import java.util.List;
import java.util.regex.Pattern;

@SuppressWarnings("java:S106")
public class RegisterCustomerUI extends AbstractFancyUI {
    private final RegisterCustomerController controller = new RegisterCustomerController();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CountryPrinter countryPrinter = new CountryPrinter();


    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                Name name = enterValidName();
                String street = enterValidStreet();
                String city = enterValidCity();
                String country = selectCountry();
                String fullAddress = street + ", " + city + ", " + country;
                Address address = new Address(fullAddress);
                String vatNumber = enterValidVatNumber(country);
                VatNumber vat = new VatNumber(vatNumber);
                CustomerType type = selectValidCustomerType();

                Customer customer = new Customer(name, address, vat, type);

                createCustomerRepresentative(customer);
                addCustomer(customer);

                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nCustomer added successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();

                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (UserCancelledException e) {
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    private CustomerType selectValidCustomerType() {

        List<String> customerTypes = controller.availableCustomerTypes();
        if (customerTypes == null || customerTypes.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customer types available." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> customerTypeListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                "\n\nChoose a Customer Type:\n" + UtilsUI.RESET, customerTypes, new CustomerTypePrinter());
        customerTypeListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerTypes);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return CustomerType.valueOf(customerTypes.get(option).trim().toUpperCase());
            }
        } while (true);
    }

    @Override
    public String headline() {
        return "Register Customer";
    }

    private void createCustomerRepresentative(Customer customer) {
        System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\n\n\nCreate Customer Representative:" + UtilsUI.RESET);
        Name repFullName = enterValidName();
        Email repEmail = enterValidEmail();
        PhoneNumber repPhone = enterValidPhoneNumber();
        if (repPhone == null) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo phone number registered. Operation canceled." + UtilsUI.RESET);
            return;
        }
        Position repPosition = enterValidPosition();
        CustomerRepresentative representative = new CustomerRepresentative(repFullName, repEmail, repPhone, repPosition, customer);
        customer.addCustomerRepresentative(representative);

        String[] credentials = enterUsernameAndPassword();
        String username = credentials[0];
        String password = credentials[1];

        controller.makeCustomerRepresentativeUser(representative, customer, username, password, repPhone);
        System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nCustomer Representative created successfully!" + UtilsUI.RESET);
    }

    private Position enterValidPosition() {
        String position;
        String positionRegex = "^[A-Za-zÀ-ÿ\\s]+$";
        do {
            try {
                position = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the representative's position (or type 'cancel' to go back): " + UtilsUI.RESET);
                assert position != null;
                if ("cancel".equalsIgnoreCase(position)) {
                    throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
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
    private String[] enterUsernameAndPassword() {
        String username;
        String password;
        ShodronePasswordPolicy passwordPolicy = new ShodronePasswordPolicy();

        do {
            username = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the representative's username (or type 'cancel' to go back): " + UtilsUI.RESET);

            if (username == null || username.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nUsername cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if ("cancel".equalsIgnoreCase(username)) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            password = UtilsUI.readPassword(UtilsUI.BOLD + "\n\nEnter the representative's password (or type 'cancel' to go back) (at least 6 characters, including a number): " + UtilsUI.RESET);

            if (password == null || password.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nPassword cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if ("cancel".equalsIgnoreCase(password)) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            Iterable<SystemUser> allUsers = userSvc.allUsers();
            boolean found = controller.checkIfUsernameIsInUse(allUsers, username, false);
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

    private Name enterValidName() {
        String name;
        String nameRegex = "^[A-Za-zÀ-ÿ\\s]+$";
        do {
            try {
                name = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the name (or type 'cancel' to go back): " + UtilsUI.RESET);
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
                }

                return new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid email. Please try again.\n" + UtilsUI.RESET);
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
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + "Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private String enterValidStreet() {
        String street;
        do {
            try {
                street = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the street (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(street)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                } else if (street == null || street.isEmpty()) {
                    System.out.println(UtilsUI.BOLD + UtilsUI.RED + "\nStreet cannot be empty. Please try again!" + UtilsUI.RESET);
                    continue;
                }
                return street;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    private String enterValidCity() {
        String city;
        do {
            try {
                city = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the city (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(city)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                } else if (city == null || city.isEmpty()) {
                    System.out.println(UtilsUI.BOLD + UtilsUI.RED + "\nCity cannot be empty. Please try again!" + UtilsUI.RESET);
                    continue;
                }
                return city;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    private String enterValidVatNumber(String vatCountry) {
        String vatNumber;
        String vatNumberRegex = "\\d+";
        do {
            try {
                vatNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the VAT Number (" + vatCountry + ") (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(vatNumber)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                } else if (vatNumber == null || vatNumber.isEmpty()) {
                    System.out.println(UtilsUI.BOLD + UtilsUI.RED + "\nVAT Number cannot be empty. Please try again!" + UtilsUI.RESET);
                    continue;
                } else if (!vatNumber.matches(vatNumberRegex)) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nVAT Number must contain only numbers. Please try again!" + UtilsUI.RESET);
                    continue;
                }
                String vatCountryCode = controller.countryCodeVatNumber(vatCountry);
                return vatCountryCode + vatNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
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
                "\n\nChoose a Country:\n" + UtilsUI.RESET, countries, countryPrinter);
        countryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(countries);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
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

    private void addCustomer(Customer customer) {
        try {
            controller.addCustomer(customer);
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
        }
    }
}