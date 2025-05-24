package inMemory;

import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

public class InMemoryShowProposalRepository extends InMemoryDomainRepository<ShowProposal, Long> implements ShowProposalRepository {
    static {
        InMemoryInitializer.init();
    }
}
