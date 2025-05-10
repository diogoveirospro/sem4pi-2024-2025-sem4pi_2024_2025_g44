package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.application.ListUsersController;
import core.User.application.RegisterUsersController;
import core.User.domain.ShodroneRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@UseCaseController
public class RegisterCustomerController {

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    // Método para registrar o cliente
    public void addCustomer(Customer customer) {
        try {
            customerRepository.save(customer); // Salva o cliente no repositório
        } catch (IntegrityViolationException e) {
            throw new IllegalArgumentException("Error saving the customer: " + e.getMessage());
        }
    }

    public List<String> availableCountries() {
        Map<String, String> map;
        map = PhoneNumber.countryCodes();
        List<String> countries = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            countries.add(entry.getValue());
        }
        return countries;
    }

    // Método para listar todos os clientes
    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }


}
