package inMemory;

import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShowProposalRepository extends InMemoryDomainRepository<ShowProposal, ShowProposalNumber> implements ShowProposalRepository {
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

    @Override
    public Iterable<ShowProposal> findAllTestingProposalsByShowRequest(ShowRequest request) {
        Iterable<ShowProposal> proposals = findAll();
        List<ShowProposal> proposalList = new ArrayList<>();
        for (ShowProposal proposal : proposals) {
            if (proposal.status().equals(ShowProposalStatus.TESTING) && proposal.request().equals(request)) {
                proposalList.add(proposal);
            }
        }
        return proposalList;
    }

    @Override
    public boolean existsByProposalNumber(ShowProposalNumber proposalNumber) {
        Iterable<ShowProposal> proposals = findAll();
        for (ShowProposal proposal : proposals) {
            if (proposal.identity().equals(proposalNumber)) {
                return true;
            }
        }
        return false;
    }
}
