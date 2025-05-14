package Shodrone.console.Customer.ui;

import Shodrone.exceptions.UserCancelledException;
import core.Customer.application.RegisterCustomerController;
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

import java.util.List;
import java.util.regex.Pattern;

@SuppressWarnings("java:S106")
public class RegisterCustomerUI extends AbstractFancyUI {
    private final RegisterCustomerController controller = new RegisterCustomerController();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    @Override
    protected boolean doShow() {
        try {
            Name name = enterValidName();
            String street = enterValidStreet();
            String city = enterValidCity();
            String country = selectCountry();
            String fullAddress = street + ", " + city + ", " + country;
            Address address = new Address(fullAddress);
            String vatNumber = enterValidVatNumber(country);
            VatNumber vat = new VatNumber( vatNumber);
            CustomerType type = selectValidCustomerType();
            Customer customer = new Customer(name, address, vat, type);
            createCustomerRepresentative(customer);
            addCustomer(customer);
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nCustomer added successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;
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

    private CustomerType selectValidCustomerType() {

        List<String> customerTypes = controller.availableCustomerTypes();
        if (customerTypes == null || customerTypes.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customer types available." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> customerTypeListWidget = new ListWidget<>(UtilsUI.BOLD + "\nChoose a Customer Type:\n" + UtilsUI.RESET, customerTypes);
        customerTypeListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerTypes);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled." + UtilsUI.RESET);
                return null;
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
        System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nCreate Customer Representative:" + UtilsUI.RESET);
        Name repFullName = enterValidName();
        Email repEmail = enterValidEmail();
        PhoneNumber repPhone = enterValidPhoneNumber();
        if (repPhone == null) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo phone number registered. Operation canceled." + UtilsUI.RESET);
            return;
        }
        String position = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Representative Position: " + UtilsUI.RESET);
        Position repPosition = new Position(position);
        CustomerRepresentative representative = new CustomerRepresentative(repFullName, repEmail, repPhone, repPosition, customer);
        customer.addCustomerRepresentative(representative);

        String[] credentials = enterUsernameAndPassword();
        String username = credentials[0];
        String password = credentials[1];

        controller.makeCustomerRepresentativeUser(representative, customer, username, password, repPhone);
        System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nCustomer Representative created successfully!" + UtilsUI.RESET);
    }

    private String[] enterUsernameAndPassword() {
        String username;
        String passwordChars;
        String password;
        ShodronePasswordPolicy passwordPolicy = new ShodronePasswordPolicy();

        do {
            username = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's username (or type 'cancel' to go back): " + UtilsUI.RESET);

            if (username == null || username.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Username cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if ("cancel".equalsIgnoreCase(username)) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            java.io.Console console = System.console();
            if (console != null) {
                passwordChars = UtilsUI.readPassword(UtilsUI.BOLD + "Enter the representative's password (or type 'cancel' to go back) (at least 6 characters, including a number): " + UtilsUI.RESET);
                password = passwordChars;
            } else {
                // Fallback in case console is not available
                password = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the representative's password (or type 'cancel' to go back) (at least 6 characters, including a number): " + UtilsUI.RESET);
            }

            if (password == null || password.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if ("cancel".equalsIgnoreCase(password)) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            Iterable<SystemUser> allUsers = userSvc.allUsers();
            boolean found = checkIfUsernameIsInUse(allUsers, username, false);

            if (found) {
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
    private Name enterValidName() {
        String name;
        String nameRegex = "^[A-Za-zÀ-ÿ\\s]+$";
        do {
            try {
                name = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the name (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(name)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
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
                email = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the email (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(email)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
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
        do {
            try {
                country = selectCountry();
                if (country == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo country selected. Operation canceled." + UtilsUI.RESET);
                    return null;
                }
                countryCode = controller.countryCode(country);
                phoneNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the phone number (or type 'cancel' to go back) (No spaces): " + UtilsUI.RESET);
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
                street = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the street (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(street)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
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
                city = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the city (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(city)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                return city;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    private String enterValidVatNumber(String vatCountry) {
        String vatNumber;
        do {
            try {
                vatNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the VAT Number (" + vatCountry + ") (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(vatNumber)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
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

        ListWidget<String> countryListWidget = new ListWidget<>(UtilsUI.BOLD + "\nChoose a Country: \n" + UtilsUI.RESET, countries);
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