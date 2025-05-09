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


@SuppressWarnings("java:S106")
public class RegisterCustomerUI extends AbstractUI {
    private final RegisterCustomerController controller = new RegisterCustomerController();

    @Override
    protected boolean doShow() {
        try {
            // Entradas para o Cliente
            final String customerName = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer Name: " + UtilsUI.RESET);
            final String customerEmail = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer E-Mail: " + UtilsUI.RESET);

            // Criar o objeto Name para o cliente
            Name name = new Name(customerName);

            // Criar o objeto Email para o cliente
            Email email = new Email(customerEmail);  // Usando o Email para o cliente

            // Entradas para o Endereço do Cliente
            final String street = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer Street: " + UtilsUI.RESET);
            final String city = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer City: " + UtilsUI.RESET);
            final String country = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer Country: " + UtilsUI.RESET);

            // Concatenando as partes do endereço em uma única string
            String fullAddress = street + ", " + city + ", " + country;

            // Criar o Address (Value Object)
            Address address = new Address(fullAddress); // Passando a string concatenada

            // Criar o VatNumber (Value Object)
            final String vatNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Customer VAT Number: " + UtilsUI.RESET);
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

    @Override
    public String headline() {
        return "Register Customer";
    }

    private void addCustomer(Customer customer) {
        try {
            controller.addCustomer(customer);
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
        }
    }
}
