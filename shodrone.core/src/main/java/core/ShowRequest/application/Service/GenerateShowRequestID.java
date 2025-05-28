package core.ShowRequest.application.Service;

import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.repositories.ShowRequestRepository;

import java.util.Random;

public class GenerateShowRequestID {

    private final Random random;

    public GenerateShowRequestID() {
        this.random = new Random();
    }

    /**
     * Generates a new proposal number that is not already present in the database.
     * This method ensures uniqueness by checking the repository.
     *
     * @return A new ShowProposalNumber instance with a unique value.
     */
    public ShowRequestID generate() {
        ShowRequestRepository repository = PersistenceContext.repositories().showRequest();
        String identifierStr;
        do {
            long identifier = random.nextInt(10000) + 1; // Generates a random number between 1 and 10000
            identifierStr = "REQ-" + identifier; // Prefixing with "REQ-"
        } while (repository.existsByProposalNumber(new ShowRequestID(identifierStr)));
        return new ShowRequestID(identifierStr);
    }

    public ShowRequestID generateWithoutRep() {
        long identifier = random.nextInt(10000) + 1;
        String identifierStr = "REQ-" + identifier; // Prefixing with "REQ-"
        return new ShowRequestID(identifierStr);
    }
}