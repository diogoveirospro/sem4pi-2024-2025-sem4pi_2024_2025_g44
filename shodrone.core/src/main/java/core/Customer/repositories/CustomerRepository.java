package core.Customer.repositories;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.repositories.DomainRepository;
import jakarta.persistence.EntityManager;

public interface CustomerRepository extends DomainRepository<VatNumber,Customer> {
    Iterable<Customer> findAllCreatedCustomers();
    Iterable<CustomerRepresentative> findAllActiveCustomerRepresentatives(Customer customer);
    Customer findCustomerByName(Name name);
    Customer findCustomerByRepresentativeEmail(String repEmail);
}
