package jpa;

import core.Persistence.Application;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class JpaShowProposalRepository extends JpaAutoTxRepository<ShowProposal, ShowProposalNumber, ShowProposalNumber> implements ShowProposalRepository {

    public JpaShowProposalRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaShowProposalRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "id");
    }


    @Override
    public Iterable<ShowProposal> findAllTestingProposals() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.status = :status", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.TESTING);
        return query.getResultList();
    }

    @Override
    public Iterable<ShowProposal> findAllTestingProposalsByShowRequest(ShowRequest request) {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.status = :status AND sp.request = :request", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.TESTING);
        query.setParameter("request", request);
        return query.getResultList();
    }

    @Override
    public boolean existsByProposalNumber(ShowProposalNumber proposalNumber) {
        final TypedQuery<Long> query = entityManager().createQuery(
                "SELECT COUNT(sp) FROM ShowProposal sp WHERE sp.proposalNumber = :proposalNumber", Long.class);
        query.setParameter("proposalNumber", proposalNumber);
        return query.getSingleResult() > 0;
    }

    @Override
    public Iterable<ShowProposal> findConfigurableProposals() {
        Iterable<ShowProposal> proposals = findAll();
        List<ShowProposal> configurableProposals = new ArrayList<>();
        for (ShowProposal proposal : proposals) {
            if (proposal.isReadyToConfigureDocument()) {
                configurableProposals.add(proposal);
            }
        }
        return configurableProposals;
    }

    @Override
    public ShowProposal findProposalById(ShowProposalNumber proposalNumber) {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.proposalNumber = :proposalNumber", ShowProposal.class);
        query.setParameter("proposalNumber", proposalNumber);
        List<ShowProposal> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
}
