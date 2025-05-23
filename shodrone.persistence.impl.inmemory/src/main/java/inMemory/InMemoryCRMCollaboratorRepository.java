package inMemory;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.Shared.domain.ValueObjects.Email;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;


public class InMemoryCRMCollaboratorRepository extends InMemoryDomainRepository<CRMCollaborator, EmailAddress> implements CRMCollaboratorRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public CRMCollaborator findByEmail(Email email) {
        Iterable<CRMCollaborator> collaborators = findAll();
        for (CRMCollaborator collaborator : collaborators) {
            if (collaborator.getEmail().equals(email)) {
                return collaborator;
            }
        }
        return null;
    }
}
