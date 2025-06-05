package jpa;

import core.Persistence.Application;
import core.ProposalDeliveryInfo.domain.Entities.ProposalDeliveryInfo;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ProposalDeliveryInfo.repositories.ProposalDeliveryInfoRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaProposalDeliveryInfoRepository extends JpaAutoTxRepository<ProposalDeliveryInfo, Long,
        ProposalDeliveryInfoCode> implements ProposalDeliveryInfoRepository {

    /**
     * Constructor for JPA Proposal Delivery Info Repository.
     * @param autoTx the transactional context
     */
    public JpaProposalDeliveryInfoRepository(final TransactionalContext autoTx) {
        super(autoTx, "deliveryCode");
    }

    /**
     * Constructor for JPA Proposal Delivery Info Repository.
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaProposalDeliveryInfoRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "deliveryCode");
    }

    /**
     * Checks if a delivery code exists in the repository.
     * @param deliveryCode the delivery code to check for existence
     * @return true if the delivery code exists, false otherwise
     */
    @Override
    public boolean existsByDeliveryCode(ProposalDeliveryInfoCode deliveryCode) {
        final TypedQuery<ProposalDeliveryInfo> query = entityManager().createQuery(
                "SELECT p FROM ProposalDeliveryInfo p WHERE p.code = :deliveryCode", ProposalDeliveryInfo.class);
        query.setParameter("deliveryCode", deliveryCode);
        return !query.getResultList().isEmpty();
    }
}
