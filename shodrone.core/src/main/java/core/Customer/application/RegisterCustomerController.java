package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
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
    private final RegisterUsersController registerUserController = new RegisterUsersController();

    public void addCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
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

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }

    public String countryCode(String country) {
        return PhoneNumber.countryCodeOfCountry(country);
    }

    public String countryCodeVatNumber(String vatNumber) {
        return VatNumber.VatCode(vatNumber);
    }

    public List<String> availableCustomerTypes() {
        List<String> customerTypes = new ArrayList<>();
        for (CustomerType type : CustomerType.values()) {
            customerTypes.add(type.toString());
        }
        return customerTypes;
    }

    public void makeCustomerRepresentativeUser(CustomerRepresentative representative, Customer customer, String username, String password, PhoneNumber repPhone) {
        Preconditions.noneNull(representative);
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.CUSTOMERREPRESENTATIVE);
        roles.add(ShodroneRoles.USER);
        Calendar createdOn = Calendar.getInstance();
        registerUserController.addUser(username, password, representative.name().toString(), representative.name().toString(), representative.email().toString(), roles, createdOn, representative.phoneNumber());
    }
}
