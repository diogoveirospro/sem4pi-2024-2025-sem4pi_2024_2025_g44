package inMemory;

import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShowProposalRepository extends InMemoryDomainRepository<ShowProposal, Long> implements ShowProposalRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<ShowProposal> findAllTestingProposals() {
        Iterable<ShowProposal> proposals = findAll();
        List<ShowProposal> proposalList = new ArrayList<>();
        for (ShowProposal proposal : proposals) {
            if (proposal.status().equals(ShowProposalStatus.TESTING)) {
                proposalList.add(proposal);
            }
        }
        return proposalList;
    }
}
