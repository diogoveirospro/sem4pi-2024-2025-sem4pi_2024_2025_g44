package jpa;

import core.Persistence.Application;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaShowProposalRepository extends JpaAutoTxRepository<ShowProposal, Long, Long> implements ShowProposalRepository {

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
}
