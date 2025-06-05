package inMemory;

import core.ProposalDeliveryInfo.domain.Entities.ProposalDeliveryInfo;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ProposalDeliveryInfo.repositories.ProposalDeliveryInfoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

/**
 * In-memory implementation of the ProposalDeliveryInfoRepository.
 */
public class InMemoryProposalDeliveryInfoRepository extends InMemoryDomainRepository<ProposalDeliveryInfo,
        ProposalDeliveryInfoCode> implements ProposalDeliveryInfoRepository {

    static {
        InMemoryInitializer.init();
    }

    /**
     * Knows if a delivery code exists in the repository.
     * @param deliveryCode the delivery code to check for existence
     * @return true if the delivery code exists, false otherwise
     */
    @Override
    public boolean existsByDeliveryCode(ProposalDeliveryInfoCode deliveryCode) {
        return findById(deliveryCode).isPresent();
    }
}
