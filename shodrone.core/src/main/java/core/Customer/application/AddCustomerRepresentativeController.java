package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UseCaseController
public class AddCustomerRepresentativeController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public void addCustomerRepresentative(CustomerRepresentative representative, Customer customer) {
        Preconditions.noneNull(representative);
        authz.ensureAuthenticatedUserHasAnyOf(Role.valueOf("CRMCOLLABORATOR"));
        customer.addCustomerRepresentative(representative);
        customerRepository.save(customer);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAll();
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
