package inMemory;

import core.CRMManager.domain.Entities.CRMManager;
import core.CRMManager.repositories.CRMManagerRepository;
import core.Shared.domain.ValueObjects.Email;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;


public class InMemoryCRMManagerRepository extends InMemoryDomainRepository<CRMManager, EmailAddress> implements CRMManagerRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public CRMManager findByEmail(Email email) {
        Iterable<CRMManager> managers = findAll();
        for (CRMManager manager : managers) {
            if (manager.email().equals(email)) {
                return manager;
            }
        }
        return null;
    }
}
