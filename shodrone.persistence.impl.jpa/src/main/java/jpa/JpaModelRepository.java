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
     * Creates and saves a new drone model with the given name and configuration,
     * if it does not already exist.
     *
     * @param modelName the name of the model
     * @param config    the wind speed and tolerance configuration
     * @return {@code true} if the model was created successfully, {@code false} if a model with the same name already exists
     */
    @Override
    public boolean createModel(ModelName modelName, Configuration config) {
        if (!validateModel(modelName)) {
            return false;
        }
        Model model = new Model(modelName, config);
        save(model);
        return true;
    }

    /**
     * Validates that a drone model with the given name does not already exist.
     *
     * @param modelName the model name to validate
     * @return {@code true} if no model with the same name exists, {@code false} otherwise
     */
    @Override
    public boolean validateModel(ModelName modelName) {
        Iterable<Model> models = findAll();
        for (Model model : models) {
            if (model.sameAs(modelName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves all drone models stored in the repository.
     *
     * @return an iterable collection of all models
     */
    @Override
    public Iterable<Model> findAllModels() {
        Iterable<Model> models = findAll();
        List<Model> result = new ArrayList<>();
        for (Model model : models) {
            result.add(model);
        }
        return result;
    }
}
