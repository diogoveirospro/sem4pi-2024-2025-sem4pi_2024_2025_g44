package jpa;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.Persistence.Application;
import core.Shared.domain.ValueObjects.Email;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaCRMCollaboratorRepository extends JpaAutoTxRepository<CRMCollaborator, EmailAddress, EmailAddress> implements CRMCollaboratorRepository {

    public JpaCRMCollaboratorRepository(final TransactionalContext autoTx) {
        super(autoTx, "email");
    }
    public JpaCRMCollaboratorRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "email ");
    }


    @Override
    public CRMCollaborator findByEmail(Email email) {
        final TypedQuery<CRMCollaborator> query = entityManager().createQuery(
                "SELECT c FROM CRMCollaborator c WHERE c.email = :email", CRMCollaborator.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
