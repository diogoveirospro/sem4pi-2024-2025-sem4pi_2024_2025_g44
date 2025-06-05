package core.ProposalDeliveryInfo.application.Service;

import core.Persistence.PersistenceContext;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ProposalDeliveryInfo.repositories.ProposalDeliveryInfoRepository;

import java.util.Random;

/**
 * Generates a unique proposal delivery code.
 */
public class GenerateProposalDeliveryCode {

    /**
     * Random instance used to generate random numbers for the proposal delivery code.
     */
    private final Random random;

    /**
     * Constructor for GenerateProposalDeliveryCode.
     * Initializes a new instance of Random to generate random numbers.
     */
    public GenerateProposalDeliveryCode() {
        this.random = new Random();
    }

    /**
     * Generates a new proposal number that is not already present in the database.
     * This method ensures uniqueness by checking the repository.
     *
     * @return A new ProposalDeliveryInfoCode instance with a unique value.
     */
    public ProposalDeliveryInfoCode generate() {
        ProposalDeliveryInfoRepository repository = PersistenceContext.repositories().deliveries();
        String deliveryCode;
        do {
            long deliveryNumber = random.nextInt(10000) + 1; // Generates a random number between 1 and 10000
            deliveryCode = "DELIVERY-" + deliveryNumber; // Prefixing with "DELIVERY-"
        } while (repository.existsByDeliveryCode(new ProposalDeliveryInfoCode(deliveryCode)));
        return new ProposalDeliveryInfoCode(deliveryCode);
    }
}
