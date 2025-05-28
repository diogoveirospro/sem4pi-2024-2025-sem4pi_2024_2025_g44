package core.ShowProposal.repositories;

import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShowProposalRepository extends DomainRepository<ShowProposalNumber, ShowProposal> {
    Iterable<ShowProposal> findAllTestingProposals();
    Iterable<ShowProposal> findAllTestingProposalsByShowRequest(ShowRequest request);
    boolean existsByProposalNumber(ShowProposalNumber proposalNumber);
}
