package inMemory;

import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.CustomerFeedback;
import core.ShowProposal.domain.ValueObjects.CustomerFeedbackStatus;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of the ShowProposalRepository.
 * This repository is used for testing purposes and does not persist data.
 */
public class InMemoryShowProposalRepository extends InMemoryDomainRepository<ShowProposal, ShowProposalNumber> implements ShowProposalRepository {

    static {
        InMemoryInitializer.init();
    }

    /**
     * Finds all show proposals with the status of TESTING.
     * @return an iterable collection of ShowProposal objects that are in the TESTING status.
     */
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

    /**
     * Finds all show proposals that are in the TESTING status for a specific show request.
     * @param request the ShowRequest for which to find testing proposals
     * @return an iterable collection of ShowProposal objects that are in the TESTING status for the given request.
     */
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

    /**
     * Checks if a proposal with the given proposal number exists in the repository.
     * @param proposalNumber the ShowProposalNumber to check for existence
     * @return true if a proposal with the given number exists, false otherwise
     */
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

    /**
     * Finds all show proposals that are ready to be configured.
     * @return an iterable collection of ShowProposal objects that are ready to be configured.
     */
    @Override
    public Iterable<ShowProposal> findConfigurableProposals() {
        Iterable<ShowProposal> proposals = findAll();
        List<ShowProposal> configurableProposals = new ArrayList<>();
        for (ShowProposal proposal : proposals) {
            if (proposal.isReadyToConfigureDocument()) {
                configurableProposals.add(proposal);
            }
        }
        return configurableProposals;
    }

    /**
     * Finds all show proposals that have been checked.
     * @return an iterable collection of ShowProposal objects that have been checked.
     */
    @Override
    public Iterable<ShowProposal> findAllCheckedProposals() {
        Iterable<ShowProposal> proposals = findAll();
        List<ShowProposal> checkedProposals = new ArrayList<>();
        for (ShowProposal proposal : proposals) {
            if (proposal.status().equals(ShowProposalStatus.ACCEPTED) || proposal.status().equals(ShowProposalStatus.REJECTED)) {
                checkedProposals.add(proposal);
            }
        }
        return checkedProposals;
    }

    /**
     * Finds all show proposals that are ready to be sent.
     * @return an iterable collection of ShowProposal objects that are ready to be sent.
     */
    @Override
    public Iterable<ShowProposal> findProposalsReadyToSend() {
        Iterable<ShowProposal> proposals = findAll();
        List<ShowProposal> readyToSendProposals = new ArrayList<>();
        for (ShowProposal proposal : proposals) {
            if (proposal.status().equals(ShowProposalStatus.READY_TO_SEND)) {
                readyToSendProposals.add(proposal);
            }
        }
        return readyToSendProposals;
    }

    /**
     * Finds all Show Proposals that have all the necessary information to generate a Show DSL.
     * @return an iterable collection of ShowProposal objects that are ready to generate Show DSL.
     */
    @Override
    public Iterable<ShowProposal> findProposalsReadyGenerateShowDSL() {
        Iterable<ShowProposal> proposals = findAll();
        List<ShowProposal> readyToGenerateDSLProposals = new ArrayList<>();
        for (ShowProposal proposal : proposals) {
            if (proposal.isReadyToGenerateShowDSL()) {
                readyToGenerateDSLProposals.add(proposal);
            }
        }
        return readyToGenerateDSLProposals;
    }

    /**
     * Updates the status and feedback of a proposal identified by its proposal number.
     * @param proposalNumber the ShowProposalNumber of the proposal to update
     * @param decision the new status to set for the proposal
     * @param feedback the feedback to set for the proposal
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateProposalStatusAndFeedback(String proposalNumber, String decision, String feedback) {
        ShowProposalNumber number = new ShowProposalNumber(proposalNumber);
        Optional<ShowProposal> proposal = findById(number);
        if (proposal.isEmpty()) {
            return false; // Proposal not found
        }

        ShowProposal proposalToUpdate = proposal.get();

        // Update the status and feedback
        if (decision.equalsIgnoreCase("ACCEPTED")) {
            proposalToUpdate.addCustomerFeedback(new CustomerFeedback(CustomerFeedbackStatus.ACCEPTED, feedback));
        } else if (decision.equalsIgnoreCase("REJECTED")) {
            proposalToUpdate.addCustomerFeedback(new CustomerFeedback(CustomerFeedbackStatus.REJECTED, feedback));
        }

        // Save the updated proposal back to the repository
        save(proposalToUpdate);
        return true; // Update successful
    }
}
