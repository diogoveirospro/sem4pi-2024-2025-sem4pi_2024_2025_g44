package jpa;

import core.Category.domain.Entities.Category;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.*;

/**
 * JPA implementation of the DroneRepository interface.
 * <p>
 * Manages persistence operations for Drone entities, including adding, removing,
 * and listing drones from the database.
 */
public class JpaDroneRepository extends JpaAutoTxRepository<Drone, Designation, Designation> implements DroneRepository {

    /**
     * Constructs a repository using a transactional context.
     *
     * @param autoTx the transactional context to use
     */
    public JpaDroneRepository(TransactionalContext autoTx) {
        super(autoTx, "serialNumber");
    }

    /**
     * Constructs a repository using a specific persistence unit name.
     *
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaDroneRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "serialNumber");
    }

    /**
     * Retrieves all active drones from the inventory.
     *
     * @return an iterable list of active drones
     */
    @Override
    public Iterable<Drone> findAllDronesInventory() {
        return entityManager()
                .createQuery("SELECT d FROM Drone d WHERE d.droneStatus = :status", Drone.class)
                .setParameter("status", DroneStatus.ACTIVE)
                .getResultList();
    }


    /**
     * Retrieves all active drones associated with a specific drone model.
     *
     * @param droneModel the model to filter by
     * @return a list of drones with the given model and active status
     */
    @Override
    public List<Drone> getDrnModelList(Model droneModel) {
        return entityManager()
                .createQuery("SELECT d FROM Drone d WHERE d.model = :model AND d.droneStatus = :status", Drone.class)
                .setParameter("model", droneModel)
                .setParameter("status", DroneStatus.ACTIVE)
                .getResultList();
    }
}
