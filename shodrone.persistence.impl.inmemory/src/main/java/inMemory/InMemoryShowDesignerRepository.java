package inMemory;

import core.Shared.domain.ValueObjects.Email;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import core.ShowDesigner.repositories.ShowDesignerRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

public class InMemoryShowDesignerRepository extends InMemoryDomainRepository<ShowDesigner, EmailAddress> implements ShowDesignerRepository {

    static {
        InMemoryInitializer.init();
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

    @Override
    public ShowDesigner findByEmail(EmailAddress email) {
        Iterable<ShowDesigner> showDesigners = findAll();
        for (ShowDesigner showDesigner : showDesigners) {
            if (showDesigner.email().equals(email)) {
                return showDesigner;
            }
        }
        return null;
    }
}
