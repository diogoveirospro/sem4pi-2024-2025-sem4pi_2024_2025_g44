package core.Daemon.reporting.proposals.repositories;

import core.Daemon.reporting.proposals.DeliveryReporting;
import eapli.framework.domain.repositories.ReportingRepository;

import java.util.List;

@ReportingRepository
public interface DeliveryReportingRepository {

    List<DeliveryReporting> findAllProposalsWithoutFeedbackByCustomer(String vatNumber);
    DeliveryReporting findProposalByDeliveryCode(String code);
}
