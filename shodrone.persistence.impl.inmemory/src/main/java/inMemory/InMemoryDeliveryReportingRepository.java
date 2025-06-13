package inMemory;

import core.Daemon.reporting.proposals.DeliveryReporting;
import core.Daemon.reporting.proposals.repositories.DeliveryReportingRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.List;

public class InMemoryDeliveryReportingRepository implements DeliveryReportingRepository {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<DeliveryReporting> findAllProposalsWithoutFeedbackByCustomer(String vatNumber) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public DeliveryReporting findProposalByDeliveryCode(String code) {
        return null;
    }
}
