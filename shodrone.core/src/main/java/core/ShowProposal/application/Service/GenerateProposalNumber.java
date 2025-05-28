package core.ShowProposal.application.Service;

import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowProposal.repositories.ShowProposalRepository;

import java.util.Random;

public class GenerateProposalNumber {
    private final Random random;

    public GenerateProposalNumber() {
        this.random = new Random();
    }

    /**
     * Generates a new proposal number that is not already present in the database.
     * This method ensures uniqueness by checking the repository.
     *
     * @return A new ShowProposalNumber instance with a unique value.
     */
    public ShowProposalNumber generate() {
        ShowProposalRepository repository = PersistenceContext.repositories().proposals();
        String proposalNumberStr;
        do {
            long proposalNumber = random.nextInt(10000) + 1; // Generates a random number between 1 and 10000
            proposalNumberStr = "PROP-" + proposalNumber; // Prefixing with "Prop-"
        } while (repository.existsByProposalNumber(new ShowProposalNumber(proposalNumberStr)));
        return new ShowProposalNumber(proposalNumberStr);
    }

    public ShowProposalNumber generateWithoutRep() {
        long proposalNumber = random.nextInt(10000) + 1;
        String proposalNumberStr = "PROP-" + proposalNumber; // Prefixing with "Prop-"
        return new ShowProposalNumber(proposalNumberStr);
    }
}