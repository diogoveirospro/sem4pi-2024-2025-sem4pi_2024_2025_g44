package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class DeactivateCustomerRepresentativeController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public void deactivateCustomerRepresentative(Customer customer, CustomerRepresentative representative) {

        Preconditions.noneNull(representative);
        CustomerRepresentative existingRepresentative = customer.findCustomerRepresentative(representative);
        if (existingRepresentative == null) {
            throw new IllegalArgumentException("Customer representative not found.");
        }
        existingRepresentative.deactivate();
        customerRepository.save(customer);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }

    public Iterable<CustomerRepresentative> listRepresentativesOfCustomer(Customer customer) {
        return customerRepository.findAllActiveCustomerRepresentatives(customer);
    }
}
