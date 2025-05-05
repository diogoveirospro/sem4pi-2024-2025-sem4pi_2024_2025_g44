package inMemory;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.CustomerRepresentativeStatus;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Customer.repositories.CustomerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, VatNumber> implements CustomerRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Customer> findAllCreatedCustomers() {
        Iterable<Customer> customers = findAll();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.status() == CustomerStatus.CREATED) {
                result.add(customer);
            }
        }
        return result;
    }

    @Override
    public Iterable<CustomerRepresentative> findAllActiveCustomerRepresentatives(Customer customer) {
        Iterable<CustomerRepresentative> customerRepresentatives = customer.representativesOfCustomer();
        List<CustomerRepresentative> result = new ArrayList<>();
        for (CustomerRepresentative customerRepresentative : customerRepresentatives) {
            if (customerRepresentative.status() == CustomerRepresentativeStatus.ACTIVE) {
                result.add(customerRepresentative);
            }
        }
        return result;
    }
}