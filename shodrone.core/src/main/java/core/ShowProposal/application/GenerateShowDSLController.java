package core.ShowProposal.application;

import core.Persistence.PersistenceContext;
import core.ShowProposal.application.Service.Show.GenerateShowDSL;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowDSLDescription;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.application.UseCaseController;

/**
 * Controller for generating Show DSL from a ShowProposal.
 */
@UseCaseController
public class GenerateShowDSLController {

    /**
     * Repository for managing ShowProposals.
     */
    private ShowProposalRepository repository = PersistenceContext.repositories().proposals();

    /**
     * Service for generating Show DSL.
     */
    private GenerateShowDSL service = new GenerateShowDSL();

    /**
     * Generates Show DSL from a given ShowProposal.
     *
     * @param proposal the ShowProposal to generate the DSL from
     * @return true if the generation was successful, false otherwise
     * @throws IllegalArgumentException if the proposal is null or not ready for generation
     */
    public boolean generateDSL(ShowProposal proposal) {
        if (proposal == null) {
            throw new IllegalArgumentException("Proposal cannot be null.");
        }
        if (!proposal.isReadyToGenerateShowDSL()) {
            throw new IllegalArgumentException("Proposal is not ready to generate Show DSL.");
        }

        try {
            ShowDSLDescription description = service.generateFrom(proposal);
            proposal.addShowDSLDescription(description);
            repository.save(proposal);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error generating Show DSL: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a list of ShowProposals that are ready to generate Show DSL.
     *
     * @return an iterable collection of ShowProposal objects that are ready to generate Show DSL
     */
    public Iterable<ShowProposal> proposalListReadyGenerateShowDSL(){
        return repository.findProposalsReadyGenerateShowDSL();
    }

}
