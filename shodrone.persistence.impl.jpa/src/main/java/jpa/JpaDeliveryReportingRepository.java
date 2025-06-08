package jpa;

import core.Daemon.reporting.proposals.DeliveryReporting;
import core.Daemon.reporting.proposals.repositories.DeliveryReportingRepository;
import core.Persistence.Application;
import core.ProposalDeliveryInfo.domain.Entities.ProposalDeliveryInfo;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class JpaDeliveryReportingRepository extends JpaTransactionalContext implements DeliveryReportingRepository {

    public JpaDeliveryReportingRepository() {
        super(Application.settings().persistenceUnitName(), Application.settings().extendedPersistenceProperties());
    }

    public JpaDeliveryReportingRepository(String persistenceUnitName) {
        super(persistenceUnitName,Application.settings().extendedPersistenceProperties());
    }

    private static List<DeliveryReporting> makeDeliveryReporting(Iterable<ProposalDeliveryInfo> deliveries) {
        List<DeliveryReporting> deliveriesReporting = new ArrayList<>();

        for (ProposalDeliveryInfo deliveryInfo : deliveries) {
            ShowProposal proposal = deliveryInfo.proposal();
            DeliveryReporting deliveryReporting = new DeliveryReporting(
                    proposal.identity(),
                    proposal.dateOfShow(),
                    proposal.timeOfShow(),
                    proposal.durationOfShow(),
                    proposal.request().location()
            );
            deliveriesReporting.add(deliveryReporting);
        }

        return deliveriesReporting;
    }

    @Override
    public List<DeliveryReporting> findAllProposalsByCustomer(String vatNumber) {
        final TypedQuery<ProposalDeliveryInfo> query = entityManager().createQuery(
                "SELECT pdi FROM ProposalDeliveryInfo pdi WHERE pdi.customer.vatNumber = :vatNumber",
                ProposalDeliveryInfo.class
        );
        query.setParameter("vatNumber", vatNumber);
        List<ProposalDeliveryInfo> results = query.getResultList();
        return makeDeliveryReporting(results);
    }
}
