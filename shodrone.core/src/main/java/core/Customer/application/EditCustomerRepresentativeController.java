package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UseCaseController
public class EditCustomerRepresentativeController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }

    public Iterable<CustomerRepresentative> listRepresentativesOfCustomer(Customer customer) {
        return customerRepository.findAllActiveCustomerRepresentatives(customer);
    }

    public void changeCustomerRepresentativeInfo(Customer customer, CustomerRepresentative representative, Email newEmail, PhoneNumber newPhone) {
        Preconditions.noneNull(representative);
        CustomerRepresentative existingRepresentative = customer.findCustomerRepresentative(representative);
        if (existingRepresentative == null) {
            throw new IllegalArgumentException("Customer representative not found.");
        }
        ShodroneUser user = userRepository.findByEmail(representative.email());
        existingRepresentative.changeInfo(newEmail, newPhone);
        customerRepository.save(customer);
        user.changeEmail(newEmail);
        user.changePhoneNumber(newPhone);
        userRepository.save(user);
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
