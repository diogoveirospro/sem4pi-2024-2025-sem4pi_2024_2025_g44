package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CountryPrinter;
import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.Customer.printer.CustomerRepresentativePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.application.EditCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User Interface for editing a customer representative's information.
 * This class extends AbstractFancyUI and implements the doShow method to display the UI.
 */
public class EditCustomerRepresentativeUI extends AbstractFancyListUI<CustomerRepresentative> {

    private final EditCustomerRepresentativeController controller = new EditCustomerRepresentativeController();
    private final CustomerPrinter printer = new CustomerPrinter();
    private final CountryPrinter countryPrinter = new CountryPrinter();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private Customer customer;

    @Override
    protected Iterable<CustomerRepresentative> elements() {
        customer = selectCustomer();

        if (customer == null) {
            return null;
        }

        return controller.listRepresentativesOfCustomer(customer);
    }

    @Override
    protected Visitor<CustomerRepresentative> elementPrinter() {
        return new CustomerRepresentativePrinter();
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD + UtilsUI.BLUE +
                "\n\nChoose a Customer Representative:\n" + UtilsUI.RESET + UtilsUI.BOLD
                + String.format("%n%-5s | %-20s | %-30s | %-30s | %-20s |", "INDEX", "NAME", "POSITION", "EMAIL", "PHONE")
                + "\n"
                + String.format("%-5s-+-%-20s-+-%-30s-+-%-30s-+-%-20s-+", "-".repeat(5), "-".repeat(20),
                "-".repeat(30), "-".repeat(30), "-".repeat(20))
                + UtilsUI.RESET;
    }

    @Override
    protected String emptyMessage() {
        return "No Customer Representatives Found!!";
    }

    @Override
    protected boolean doShow() {
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
        try {
            Email newEmail = enterValidEmail();
            PhoneNumber newPhone = enterValidPhoneNumber();

            controller.changeCustomerRepresentativeInfo(customer, selectedRep, newEmail, newPhone);
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nRepresentative info updated successfully!" + UtilsUI.RESET);
        } catch (UserCancelledException e) {
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Error: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }

        UtilsUI.goBackAndWait();
        return true;
    }


    @Override
    public String headline() {
        return "Edit Customer Representative Information";
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
                if (phoneNumber.isEmpty()) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nPhone number cannot be empty." + UtilsUI.RESET);
                    continue;
                }
                return new PhoneNumber(countryCode, phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Please try again." + UtilsUI.RESET);
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
                "\n\nChoose a Country: \n" + UtilsUI.RESET, countries, countryPrinter);
        countryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(countries);
            if (option == -2) {
                throw new UserCancelledException("Selection cancelled by user.");
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
}