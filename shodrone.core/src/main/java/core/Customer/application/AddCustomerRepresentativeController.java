package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.application.RegisterUsersController;
import core.User.domain.ShodroneRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.validations.Preconditions;

import java.util.*;

@UseCaseController
public class AddCustomerRepresentativeController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final RegisterUsersController registerUserController = new RegisterUsersController();

    public void addCustomerRepresentative(CustomerRepresentative representative, Customer customer, String username, String password, PhoneNumber phoneNumber) {
        Preconditions.noneNull(representative);
        if (customer.representativesOfCustomer().contains(representative)) {
            throw new IllegalArgumentException("Customer already has this representative");
        }
        customer.addCustomerRepresentative(representative);
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.CUSTOMERREPRESENTATIVE);
        roles.add(ShodroneRoles.USER);
        Calendar createdOn = Calendar.getInstance();
        registerUserController.addUser(username, password, representative.name().toString(), representative.name().toString(), representative.email().toString(), roles, createdOn, representative.phoneNumber());
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

}
