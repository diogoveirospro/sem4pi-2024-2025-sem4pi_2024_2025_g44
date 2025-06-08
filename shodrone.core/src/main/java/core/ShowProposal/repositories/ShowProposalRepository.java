package core.ShowProposal.repositories;

import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShowProposalRepository extends DomainRepository<ShowProposalNumber, ShowProposal> {

    /**
     * Finds all show proposals that are in the TESTING status.
     * @return an iterable collection of ShowProposal objects that are in the TESTING status.
     */
    Iterable<ShowProposal> findAllTestingProposals();

    /**
     * Finds all show proposals that are in the TESTING status for a specific show request.
     * @param request the ShowRequest for which to find testing proposals
     * @return an iterable collection of ShowProposal objects that are in the TESTING status for the given request.
     */
    Iterable<ShowProposal> findAllTestingProposalsByShowRequest(ShowRequest request);

    /**
     * Checks if a proposal with the given proposal number exists in the repository.
     * @param proposalNumber the ShowProposalNumber to check for existence
     * @return true if a proposal with the given number exists, false otherwise
     */
    boolean existsByProposalNumber(ShowProposalNumber proposalNumber);

    /**
     * Finds all ShowProposals that are ready to be configured.
     * @return an iterable collection of ShowProposal objects that are ready to be configured.
     */
    Iterable<ShowProposal> findConfigurableProposals();

    /**
     * Finds all ShowProposals that have been checked.
     * @return an iterable collection of ShowProposal objects that have been checked.
     */
    Iterable <ShowProposal> findAllCheckedProposals();

    /**
     * Finds all ShowProposals that are ready to be sent.
     * @return an iterable collection of ShowProposal objects that are ready to be sent.
     */
    Iterable<ShowProposal> findProposalsReadyToSend();

    /**
     * Finds all Show Proposals that have all the necessary information to generate a Show DSL.
     * @return an iterable collection of ShowProposal objects that are ready to generate Show DSL.
     */
    Iterable<ShowProposal> findProposalsReadyGenerateShowDSL();

    /**
     * Updates the status and feedback of a proposal identified by its proposal number.
     * @param proposalNumber the ShowProposalNumber of the proposal to update
     * @param decision the new status to set for the proposal
     * @param feedback the feedback to set for the proposal
     * @return true if the update was successful, false otherwise
     */
    boolean updateProposalStatusAndFeedback(String proposalNumber, String decision, String feedback);
}

