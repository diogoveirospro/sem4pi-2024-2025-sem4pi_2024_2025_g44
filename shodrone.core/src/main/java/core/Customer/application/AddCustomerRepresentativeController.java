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
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.validations.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.parameters.P;

import java.util.*;

@UseCaseController
public class AddCustomerRepresentativeController {
    private static final Logger LOGGER = LogManager.getLogger(AddCustomerRepresentativeController.class);
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final RegisterUsersController userController = new RegisterUsersController();
    private final ListUsersController listUserController = new ListUsersController();

    public void addCustomerRepresentative(CustomerRepresentative representative, Customer customer, String username, String password, PhoneNumber phoneNumber) {
        Preconditions.noneNull(representative);
        customer.addCustomerRepresentative(representative);
        // Register the user
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.ADMIN);
        roles.add(ShodroneRoles.USER);
        registerUser(username, password, representative.name().toString(), representative.representee().name().toString(),
                representative.email().toString(), roles, phoneNumber);
        customerRepository.save(customer);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
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

    public String countryCode(String country) {
        return PhoneNumber.countryCodeOfCountry(country);
    }

    protected SystemUser registerUser(final String username, final String password, final String firstName,
                                      final String lastName, final String email, final Set<Role> roles, final PhoneNumber phoneNumber) {

        SystemUser u = null;
        try {
            u = userController.addUser(username, password, firstName, lastName, email, roles, phoneNumber);
            LOGGER.debug("»»» {}", username);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            u = listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
        return u;
    }
}
