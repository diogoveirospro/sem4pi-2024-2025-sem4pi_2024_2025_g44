package inMemory;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Customer.repositories.CustomerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, VatNumber> implements CustomerRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<CustomerRepresentative> findAllCustomerRepresentatives(Customer customer) {
        return new ArrayList<>(customer.representativesOfCustomer());
    }
}