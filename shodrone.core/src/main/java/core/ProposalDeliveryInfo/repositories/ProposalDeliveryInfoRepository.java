package core.ProposalDeliveryInfo.repositories;

import core.Daemon.reporting.proposals.DeliveryReporting;
import core.ProposalDeliveryInfo.domain.Entities.ProposalDeliveryInfo;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ProposalDeliveryInfoRepository extends DomainRepository<ProposalDeliveryInfoCode, ProposalDeliveryInfo> {

    /**
     * Checks if a delivery code exists in the repository.
     * @param deliveryCode the delivery code to check for existence
     * @return true if the delivery code exists, false otherwise
     */
    boolean existsByDeliveryCode(ProposalDeliveryInfoCode deliveryCode);

}
