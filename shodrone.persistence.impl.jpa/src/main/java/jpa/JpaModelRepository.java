package jpa;

import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA implementation of the {@link ModelRepository}.
 * <p>
 * This repository handles persistence operations for {@link Model} entities,
 * including creating, validating, and retrieving drone models.
 */
public class JpaModelRepository extends JpaAutoTxRepository<Model, Designation, Designation> implements ModelRepository {

    /**
     * Constructs a {@code JpaModelRepository} with a given transactional context.
     *
     * @param autoTx the transactional context to manage transactions
     */
    public JpaModelRepository(TransactionalContext autoTx) {
        super(autoTx, "modelName");
    }

    /**
     * Constructs a {@code JpaModelRepository} with a specified persistence unit name.
     *
     * @param persistenceUnitName the name of the JPA persistence unit
     */
    public JpaModelRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "modelName");
    }

    /**
     * Retrieves all drone models using a JPQL query.
     *
     * @return an iterable collection of all models
     */
    @Override
    public Iterable<Model> findAllModels() {
        final TypedQuery<Model> query = entityManager().createQuery(
                "SELECT m FROM Model m", Model.class);
        return query.getResultList();
    }
}
