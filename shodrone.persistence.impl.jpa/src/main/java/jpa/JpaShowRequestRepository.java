package jpa;

import core.Persistence.Application;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

public class JpaShowRequestRepository extends JpaAutoTxRepository<ShowRequest, ShowRequestID, ShowRequestID> implements ShowRequestRepository {

    public JpaShowRequestRepository(final TransactionalContext autoTx) {
        super(autoTx, "showRequestID");
    }

    public JpaShowRequestRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "showRequestID");
    }

    @Override
    public Iterable<ShowRequest> findAllCreatedShowRequests() {
        final TypedQuery<ShowRequest> query = entityManager().createQuery(
                "SELECT s FROM ShowRequest s WHERE s.status = :status", ShowRequest.class);
        query.setParameter("status", ShowRequestStatus.CREATED);
        return query.getResultList();
    }
}