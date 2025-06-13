package jpa;

import core.Customer.domain.ValueObjects.VatNumber;
import core.Daemon.reporting.shows.ShowReporting;
import core.Daemon.reporting.shows.repositories.ShowReportingRepository;
import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Model;
import core.Persistence.Application;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.Entities.ShowConfigurationEntry;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaShowReportingRepository extends JpaTransactionalContext implements ShowReportingRepository {


    public JpaShowReportingRepository() {
        super(Application.settings().persistenceUnitName(), Application.settings().extendedPersistenceProperties());
    }

    public JpaShowReportingRepository(String persistenceUnitName) {
        super(persistenceUnitName,Application.settings().extendedPersistenceProperties());
    }

    @Override
    public List<ShowReporting> findShowsOfCustomer(String vatNumber) {
        List<ShowRequest> customerRequests = acceptedShowRequests(vatNumber);

        List<ShowProposal> acceptedProposals = acceptedProposalOfEachRequest(customerRequests);

        Map<ShowRequest, ShowProposal> map = mapOfShowsInformation(acceptedProposals);

        return makeShowReporting(map);
    }

    private static List<ShowReporting> makeShowReporting(Map<ShowRequest, ShowProposal> map) {
        List<ShowReporting> shows = new ArrayList<>();
        for (Map.Entry<ShowRequest, ShowProposal> entry : map.entrySet()) {
            ShowRequest request = entry.getKey();
            ShowProposal proposal = entry.getValue();

            Map<Model, List<Drone>> droneConfiguration = createMapOfModelsAndDrones(proposal);

            ShowReporting show = new ShowReporting(
                    proposal.identity(),
                    request.identity(),
                    droneConfiguration,
                    proposal.video(),
                    proposal.configuration().figures(),
                    request.showDescription(),
                    request.location(),
                    proposal.dateOfShow(),
                    proposal.timeOfShow(),
                    proposal.quantityOfDrones(),
                    proposal.insurance(),
                    proposal.durationOfShow()
            );
            shows.add(show);
        }
        return shows;
    }

    private static Map<Model, List<Drone>> createMapOfModelsAndDrones(ShowProposal proposal) {
        Map<Model, List<Drone>> droneConfiguration = new HashMap<>();
        for (ShowConfigurationEntry entryConfig : proposal.configuration().showConfiguration()) {
            droneConfiguration
                    .computeIfAbsent(entryConfig.model(), k -> new ArrayList<>())
                    .add(entryConfig.drone());
        }
        return droneConfiguration;
    }

    private static Map<ShowRequest, ShowProposal> mapOfShowsInformation(List<ShowProposal> acceptedProposals) {
        Map<ShowRequest, ShowProposal> map = new HashMap<>();
        for (ShowProposal proposal : acceptedProposals) {
            ShowRequest request = proposal.request();
            if (!map.containsKey(request)) {
                map.put(request, proposal);
            }
        }
        return map;
    }

    private List<ShowProposal> acceptedProposalOfEachRequest(List<ShowRequest> customerRequests) {
        // IF there is a bug here, it is because of the entityManager()
        final TypedQuery<ShowProposal> proposalQuery = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.request IN :requests AND sp.status = :status", ShowProposal.class);
        proposalQuery.setParameter("requests", customerRequests);
        proposalQuery.setParameter("status", ShowProposalStatus.ACCEPTED);
        return proposalQuery.getResultList();
    }

    private List<ShowRequest> acceptedShowRequests(String vatNumber) {
        // IF there is a bug here, it is because of the entityManager()
        final TypedQuery<ShowRequest> requestQuery = entityManager().createQuery(
                "SELECT sr FROM ShowRequest sr WHERE sr.customer.vat = :vatNumber AND sr.status = :status", ShowRequest.class);
        requestQuery.setParameter("vatNumber", new VatNumber(vatNumber));
        requestQuery.setParameter("status", ShowRequestStatus.ACCEPTED);
        return requestQuery.getResultList();
    }
}