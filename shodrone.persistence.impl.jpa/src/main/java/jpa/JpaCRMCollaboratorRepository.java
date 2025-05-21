package jpa;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaCRMCollaboratorRepository extends JpaAutoTxRepository<CRMCollaborator, EmailAddress, EmailAddress> implements CRMCollaboratorRepository {

    public JpaCRMCollaboratorRepository(final TransactionalContext autoTx) {
        super(autoTx, "email");
    }
    public JpaCRMCollaboratorRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "email ");
    }


}
