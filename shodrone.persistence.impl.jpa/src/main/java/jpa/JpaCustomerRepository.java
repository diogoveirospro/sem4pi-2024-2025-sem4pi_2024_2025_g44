package jpa;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.CustomerRepresentativeStatus;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, VatNumber, VatNumber> implements CustomerRepository {

    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "vatNumber");
    }

    public JpaCustomerRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "vatNumber");
    }

    @Override
    public Iterable<Customer> findAllCreatedCustomers() {
        final TypedQuery<Customer> query = entityManager().createQuery(
                "SELECT c FROM Customer c WHERE c.status = :status", Customer.class);
        query.setParameter("status", CustomerStatus.CREATED);
        return query.getResultList();
    }

    @Override
    public Iterable<CustomerRepresentative> findAllActiveCustomerRepresentatives(Customer customer) {
        final TypedQuery<CustomerRepresentative> query = entityManager().createQuery(
                "SELECT cr FROM CustomerRepresentative cr WHERE cr.customer = :customer AND cr.status = :status",
                CustomerRepresentative.class);
        query.setParameter("customer", customer);
        query.setParameter("status", CustomerRepresentativeStatus.ACTIVE);
        return query.getResultList();
    }
}