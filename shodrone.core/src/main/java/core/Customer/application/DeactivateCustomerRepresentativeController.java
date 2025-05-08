package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class DeactivateCustomerRepresentativeController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();
    public void deactivateCustomerRepresentative(Customer customer, CustomerRepresentative representative) {
        Preconditions.noneNull(customer, representative);
        CustomerRepresentative existingRepresentative = customer.findCustomerRepresentative(representative);
        if (existingRepresentative == null) {
            throw new IllegalArgumentException("Customer representative not found.");
        }
        ShodroneUser user = userRepository.findByEmail(existingRepresentative.email());
        if (user == null) {
            throw new IllegalArgumentException("Associated user not found.");
        }
        SystemUser systemUser = user.user();
        if (!systemUser.isActive()) {
            throw new IllegalStateException("User is already inactive.");
        }
        existingRepresentative.deactivate();
        systemUser.deactivate(CurrentTimeCalendars.now());
        customerRepository.save(customer);
        userRepository.save(user);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }

    public Iterable<CustomerRepresentative> listRepresentativesOfCustomer(Customer customer) {
        return customerRepository.findAllActiveCustomerRepresentatives(customer);
    }
}
