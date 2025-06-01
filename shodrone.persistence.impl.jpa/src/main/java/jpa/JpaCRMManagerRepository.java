package jpa;

import core.CRMManager.domain.Entities.CRMManager;
import core.CRMManager.repositories.CRMManagerRepository;
import core.Persistence.Application;
import core.Shared.domain.ValueObjects.Email;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaCRMManagerRepository extends JpaAutoTxRepository<CRMManager, EmailAddress, EmailAddress> implements CRMManagerRepository {

    public JpaCRMManagerRepository(final TransactionalContext autoTx) {
        super(autoTx, "email");
    }
    public JpaCRMManagerRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "email ");
    }


    @Override
    public CRMManager findByEmail(Email email) {
        final TypedQuery<CRMManager> query = entityManager().createQuery(
                "SELECT c FROM CRMManager c WHERE c.email = :email", CRMManager.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
