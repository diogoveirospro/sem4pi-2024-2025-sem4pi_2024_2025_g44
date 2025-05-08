package Shodrone.console.Customer.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.Customer.printer.CustomerRepresentativePrinter;
import core.Customer.application.EditCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * User Interface for editing a customer representative's information.
 * This class extends AbstractFancyUI and implements the doShow method to display the UI.
 */
public class EditCustomerRepresentativeUI extends AbstractFancyUI {

    private final EditCustomerRepresentativeController controller = new EditCustomerRepresentativeController();


    @Override
    protected boolean doShow() {
        try {
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

            Email newEmail = enterValidEmail();
            PhoneNumber newPhone = enterValidPhoneNumber();

            controller.changeCustomerRepresentativeInfo(customer, representative, newEmail, newPhone);
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Customer Representative information updated successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Edit Customer Representative Information";
    }

    private Customer selectCustomer() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        ListWidget<Customer> customerListWidget = new ListWidget<>("Choose a Customer", customerList, new CustomerPrinter());
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

    private CustomerRepresentative selectCustomerRepresentative(Customer customer) {
        Iterable<CustomerRepresentative> representatives = controller.listRepresentativesOfCustomer(customer);
        if (representatives == null || !representatives.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customer representatives available." + UtilsUI.RESET);
            return null;
        }

        List<CustomerRepresentative> representativeList = new ArrayList<>();
        representatives.forEach(representativeList::add);

        ListWidget<CustomerRepresentative> representativeListWidget = new ListWidget<>("Choose a Representative", representativeList, new CustomerRepresentativePrinter());
        representativeListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(representativeList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
                return null;
            }

            if (option < 1 || option > representativeList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return representativeList.get(option - 1);
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
                phoneNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new representative's phone number: " + UtilsUI.RESET);
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

    private Email enterValidEmail() {
        String email;
        do {
            try {
                email = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new representative's email: " + UtilsUI.RESET);
                return new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid email. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }
}