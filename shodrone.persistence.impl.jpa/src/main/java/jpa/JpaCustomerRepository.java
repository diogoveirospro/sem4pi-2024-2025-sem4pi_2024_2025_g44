package jpa;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.CustomerRepresentativeStatus;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.Application;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    @Override
    public Customer findCustomerByName(Name name) {
        final TypedQuery<Customer> query = entityManager().createQuery(
                "SELECT c FROM Customer c WHERE c.name = :name", Customer.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Customer findCustomerByRepresentativeEmail(String repEmail) {
        Email email = new Email(repEmail);
        final TypedQuery<Customer> query = entityManager().createQuery(
                "SELECT c FROM Customer c JOIN c.representatives r WHERE r.email = :repEmail",
                Customer.class);
        query.setParameter("repEmail", email);
        return query.getSingleResult();

    }

}