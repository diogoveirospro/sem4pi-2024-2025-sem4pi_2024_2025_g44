package Shodrone.console.Customer.ui;

import core.Customer.application.RegisterCustomerController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.application.RegisterUsersController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import shodrone.presentation.UtilsUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


@SuppressWarnings("java:S106")
public class RegisterCustomerUI extends AbstractUI {
    private final RegisterCustomerController controller = new RegisterCustomerController();

    @Override
    protected boolean doShow() {
        try {
            // Entradas para o Cliente
            final String customerName = enterValidName();
            // Criar o objeto Name para o cliente
            Name name = new Name(customerName);


            // Entradas para o Endereço do Cliente
            final String street = enterValidStreet();  // Validando a entrada de rua
            final String city = enterValidCity();  // Validando a entrada de cidade
            final String country = selectCountry();  // Validando a seleção de país

            // Concatenacao das partes do endereço em uma única string
            String fullAddress = street + ", " + city + ", " + country;

            // Criar o Address (Value Object)
            Address address = new Address(fullAddress); // String concatenada

            // Criar o VatNumber (Value Object)
            final String vatNumber = enterValidVatNumber();
            VatNumber vat = new VatNumber(vatNumber);

            // Definir o CustomerStatus e CustomerType (Value Objects)
            CustomerStatus status = CustomerStatus.CREATED;  // Status padrão
            CustomerType type = CustomerType.REGULAR;        // Tipo padrão

            // Criar o objeto Customer com todos os parâmetros
            Customer customer = new Customer(name, address, vat, type);

            // Registrar o Cliente no sistema
            addCustomer(customer);

            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Customer added successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();

            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    private String selectCountry() {
        List<String> countries = controller.availableCountries();
        if (countries == null || countries.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No countries available." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> countryListWidget = new ListWidget<>("Choose a Country", countries);
        countryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(countries);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled. Please try again." + UtilsUI.RESET);
                continue;  // Permite tentar novamente
            }

            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return countries.get(option); // Retorna o país selecionado
            }
        } while (true);
    }
    @Override
    public String headline() {
        return "Register Customer";
    }
    private String enterValidStreet() {
        String street;
        String streetRegex = "^[A-Za-zÀ-ÿ0-9\\s]+$";  // Aceita letras, números e espaços
        do {
            try {
                street = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer Street: " + UtilsUI.RESET);
                if (street.isEmpty()) {
                    throw new IllegalArgumentException("Street cannot be empty");
                }
                if (!Pattern.matches(streetRegex, street)) {
                    throw new IllegalArgumentException("Street can only contain letters, numbers, and spaces");
                }
                return street;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    private String enterValidCity() {
        String city;
        String cityRegex = "^[A-Za-zÀ-ÿ\\s]+$";  // Aceita apenas letras e espaços
        do {
            try {
                city = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer City: " + UtilsUI.RESET);
                if (city.isEmpty()) {
                    throw new IllegalArgumentException("City cannot be empty");
                }
                if (!Pattern.matches(cityRegex, city)) {
                    throw new IllegalArgumentException("City can only contain letters and spaces");
                }
                return city;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    private String enterValidVatNumber() {
        String vatNumber;
        do {
            try {
                vatNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer VAT Number: " + UtilsUI.RESET);
                if (vatNumber.isEmpty()) {
                    throw new IllegalArgumentException("VAT Number cannot be empty");
                }
                return vatNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    private String enterValidName() {
        String name;
        String nameRegex = "^[A-Za-zÀ-ÿ\\s]+$";  // Aceita apenas letras e espaços
        do {
            try {
                name = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer Name: " + UtilsUI.RESET);
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty");
                }
                if (!Pattern.matches(nameRegex, name)) {
                    throw new IllegalArgumentException("Name can only contain letters and spaces");
                }
                return name;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
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
