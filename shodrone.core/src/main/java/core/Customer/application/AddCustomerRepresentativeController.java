package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.application.RegisterUsersController;
import core.User.domain.ShodroneRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.validations.Preconditions;

import java.util.*;

@UseCaseController
public class AddCustomerRepresentativeController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final RegisterUsersController registerUserController = new RegisterUsersController();
    private final Iterable<SystemUser> allUsers = AuthzRegistry.userService().allUsers();

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

        String[] nameParts = representative.name().toString().trim().split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "Representative";

        registerUserController.addUser(username, password, firstName, lastName, representative.email().toString(), roles, createdOn, representative.phoneNumber());
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

    public boolean checkIfUsernameIsInUse(String username) {
        boolean found = false;
        for (SystemUser user : allUsers) {
            if (user.username().equals(Username.valueOf(username))) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean checkIfEmailIsInUse(String email) {
        boolean found = false;
        for (SystemUser user : allUsers) {
            if (user.email().toString().equals(email)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
