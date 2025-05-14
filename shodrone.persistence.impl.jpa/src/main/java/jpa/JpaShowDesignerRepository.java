package jpa;

import core.Persistence.Application;
import core.Shared.domain.ValueObjects.Email;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import core.ShowDesigner.repositories.ShowDesignerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.LockModeType;

public class JpaShowDesignerRepository extends JpaAutoTxRepository<ShowDesigner, Long, EmailAddress> implements ShowDesignerRepository {

    public JpaShowDesignerRepository(final TransactionalContext autoTx) {
        super(autoTx, "email");
    }

    public JpaShowDesignerRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "email");
    }

    @Override
    public ShowDesigner findByEmail(EmailAddress email) {
        return matchOne(LockModeType.valueOf("e.email = :email"), "email", email).orElse(null);
    }

    @Override
    public ShowDesigner findByEmail(Email email) {
        Iterable<ShowDesigner> showDesigners = findAll();
        for (ShowDesigner showDesigner : showDesigners) {
            if (showDesigner.email().equals(email)) {
                return showDesigner;
            }
        }
        return null;
    }

}
