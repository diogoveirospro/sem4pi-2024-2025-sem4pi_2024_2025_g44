package jpa;

import core.Persistence.Application;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.CustomerFeedback;
import core.ShowProposal.domain.ValueObjects.CustomerFeedbackStatus;
import core.ShowProposal.domain.ValueObjects.ShowProposalNumber;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * JPA implementation of the ShowProposalRepository interface.
 * This class provides methods to interact with the ShowProposal entities in the database.
 */
public class JpaShowProposalRepository extends JpaAutoTxRepository<ShowProposal, ShowProposalNumber, ShowProposalNumber> implements ShowProposalRepository {

    /**
     * Constructor for JpaShowProposalRepository with a TransactionalContext.
     *
     * @param autoTx the TransactionalContext for managing transactions
     */
    public JpaShowProposalRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * Constructor for JpaShowProposalRepository with a persistence unit name.
     *
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaShowProposalRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "id");
    }

    /**
     * Find all ShowProposals that are in the TESTING status.
     *
     * @return an iterable collection of ShowProposals in the TESTING status
     */
    @Override
    public Iterable<ShowProposal> findAllTestingProposals() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.status = :status", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.TESTING);
        return query.getResultList();
    }

    /**
     * Find all ShowProposals that are in the TESTING status for a specific ShowRequest.
     *
     * @param request the ShowRequest for which to find testing proposals
     * @return an iterable collection of ShowProposals in the TESTING status for the given ShowRequest
     */
    @Override
    public Iterable<ShowProposal> findAllTestingProposalsByShowRequest(ShowRequest request) {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.status = :status AND sp.request = :request", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.TESTING);
        query.setParameter("request", request);
        return query.getResultList();
    }

    /**
     * Knows if a ShowProposal exists by its proposal number.
     *
     * @param proposalNumber the ShowProposalNumber to check for existence
     * @return true if a ShowProposal with the given proposal number exists, false otherwise
     */
    @Override
    public boolean existsByProposalNumber(ShowProposalNumber proposalNumber) {
        final TypedQuery<Long> query = entityManager().createQuery(
                "SELECT COUNT(sp) FROM ShowProposal sp WHERE sp.proposalNumber = :proposalNumber", Long.class);
        query.setParameter("proposalNumber", proposalNumber);
        return query.getSingleResult() > 0;
    }

    /**
     * Finds all ShowProposals that are ready to be configured.
     *
     * @return an iterable collection of ShowProposals that are ready to configure documents
     */
    @Override
    public Iterable<ShowProposal> findConfigurableProposals() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp " +
                        "WHERE sp.request.customer IS NOT NULL " +
                        "AND sp.proposalNumber IS NOT NULL " +
                        "AND sp.createdAt IS NOT NULL " +
                        "AND sp.video IS NOT NULL " +
                        "AND sp.insurance IS NOT NULL " +
                        "AND sp.request.location IS NOT NULL " +
                        "AND sp.dateOfShow IS NOT NULL " +
                        "AND sp.timeOfShow IS NOT NULL " +
                        "AND sp.durationOfShow IS NOT NULL " +
                        "AND sp.configuration IS NOT NULL " +
                        "AND sp.configuration.configurationFigures IS NOT EMPTY " +
                        "AND sp.configuration.configurationDrones IS NOT EMPTY " +
                        "AND sp.status = :status",
                ShowProposal.class
        );
        query.setParameter("status", ShowProposalStatus.TESTING);

        return query.getResultList();
    }

    /**
     * Finds all ShowProposals that have been checked (either accepted or rejected).
     *
     * @return an iterable collection of ShowProposals that have been checked
     */
    @Override
    public Iterable<ShowProposal> findAllCheckedProposals() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.customerFeedback.feedbackStatus IN (:status1, :status2)", ShowProposal.class);
        query.setParameter("status1", CustomerFeedbackStatus.ACCEPTED);
        query.setParameter("status2", CustomerFeedbackStatus.REJECTED);
        return query.getResultList();
    }

    /**
     * Finds all ShowProposals that are ready to be sent.
     *
     * @return an iterable collection of ShowProposals that are ready to send
     */
    @Override
    public Iterable<ShowProposal> findProposalsReadyToSend() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.status = :status", ShowProposal.class);
        query.setParameter("status", ShowProposalStatus.READY_TO_SEND);
        return query.getResultList();
    }

    /**
     * Finds all ShowProposals that have all the necessary information to generate a Show DSL.
     *
     * @return an iterable collection of ShowProposals that are ready to generate Show DSL
     */
    @Override
    public Iterable<ShowProposal> findProposalsReadyGenerateShowDSL() {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "SELECT sp FROM ShowProposal sp WHERE sp.configuration IS NOT NULL " +
                        "AND sp.configuration.configurationFigures IS NOT EMPTY",
                ShowProposal.class);
        return query.getResultList();
    }

    public boolean updateProposalStatusAndFeedback(String proposalNumber, String decision, String feedback) {
        final TypedQuery<ShowProposal> query = entityManager().createQuery(
                "UPDATE ShowProposal sp SET sp.customerFeedback = :customerFeedback " +
                        "WHERE sp.proposalNumber = :proposalNumber", ShowProposal.class);

        String formattedFeedback = feedback.replace("_", " "); // Replace underscores with spaces for feedback text

        query.setParameter("customerFeedback", new CustomerFeedback(CustomerFeedbackStatus.valueOf(decision), formattedFeedback));
        query.setParameter("proposalNumber", new ShowProposalNumber(proposalNumber));

        int updatedCount = query.executeUpdate();
        return updatedCount > 0;
    }

}
