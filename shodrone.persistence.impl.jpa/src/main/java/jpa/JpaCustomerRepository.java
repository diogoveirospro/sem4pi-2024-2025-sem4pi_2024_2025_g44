package jpa;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.Application;
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

    /**
     * This will return all customer representatives of a given customer.
     * @param customer the customer whose representatives we want to find
     * @return an iterable of customer representatives
     */
    @Override
    public Iterable<CustomerRepresentative> findAllCustomerRepresentatives(Customer customer) {
        final TypedQuery<CustomerRepresentative> query = entityManager().createQuery(
                "SELECT cr FROM CustomerRepresentative cr WHERE cr.customer = :customer",
                CustomerRepresentative.class
        );
        query.setParameter("customer", customer);
        return query.getResultList();
    }
}