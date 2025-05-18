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
        super(autoTx, "modelName");
    }

    /**
     * Constructs a repository using a specific persistence unit name.
     *
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaDroneRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "modelName");
    }

    /**
     * Adds a new drone to the inventory if its serial number is not already in use
     * by another active drone.
     *
     * @param serialNumber the serial number of the new drone
     * @param model        the drone model
     * @return true if added successfully, false otherwise
     */
    @Override
    public boolean addDrone(SerialNumber serialNumber, Model model) {
        if (!validateDrone(serialNumber)) {
            return false;
        }

        Map<Date, String> mapRemovalReason = new HashMap<>();
        String startingMsg = "Drone Added";
        mapRemovalReason.put(new Date(), startingMsg);
        RemovalReason removalReason = new RemovalReason(mapRemovalReason);

        Drone drone = new Drone(serialNumber, model, removalReason, DroneStatus.ACTIVE);
        save(drone);
        return true;
    }

    /**
     * Validates whether a drone with the given serial number can be added (i.e., no duplicate active drone).
     *
     * @param serialNumber the serial number to check
     * @return true if it's valid to add, false if a drone with same serial and active status exists
     */
    private boolean validateDrone(SerialNumber serialNumber) {
        for (Drone drone : findAll()) {
            if (drone.identity().equals(serialNumber) && drone.droneStatus() == DroneStatus.ACTIVE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes a drone from the inventory, updating its status and logging the removal reason.
     *
     * @param drone       the drone to remove
     * @param removReason the reason for removal
     * @return true if removal succeeded, false otherwise
     */
    @Override
    public boolean removeDrone(Drone drone, String removReason) {
        Iterable<Drone> drones = findAllDronesInventory();

        if (!validateRemoval(drone, drones)) {
            return false;
        }

        addDrnRemovData(drone, removReason);
        changeDrnStatRemv(drone);
        return true;
    }

    /**
     * Validates if the given drone exists in the active inventory and is eligible for removal.
     *
     * @param drone  the drone to validate
     * @param drones the iterable list of active drones
     * @return true if the drone is valid for removal, false otherwise
     */
    public boolean validateRemoval(Drone drone, Iterable<Drone> drones) {
        for (Drone droneTest : drones) {
            if (drone.equals(droneTest) && drone.droneStatus().equals(DroneStatus.ACTIVE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all active drones from the inventory.
     *
     * @return an iterable list of active drones
     */
    @Override
    public Iterable<Drone> findAllDronesInventory() {
        Iterable<Drone> drones = findAll();
        List<Drone> droneList = new ArrayList<>();

        for (Drone drone : drones) {
            if (drone.droneStatus().equals(DroneStatus.ACTIVE)) {
                droneList.add(drone);
            }
        }
        return droneList;
    }

    /**
     * Adds a removal reason to the drone's record.
     *
     * @param drone       the drone to update
     * @param removReason the reason for removal
     */
    public void addDrnRemovData(Drone drone, String removReason) {
        drone.removalReason().addReason(removReason);
        save(drone);
    }

    /**
     * Changes the drone's status to REMOVED.
     *
     * @param drone the drone to update
     */
    public void changeDrnStatRemv(Drone drone) {
        drone.setDroneStatus(DroneStatus.REMOVED);
        save(drone);
    }

    /**
     * Retrieves all active drones associated with a specific drone model.
     *
     * @param droneModel the model to filter by
     * @return a list of drones with the given model and active status
     */
    @Override
    public List<Drone> getDrnModelList(Model droneModel) {
        List<Drone> drnModelList = new ArrayList<>();
        for (Drone drone : findAll()) {
            if (drone.model().sameAs(droneModel) && drone.droneStatus() == DroneStatus.ACTIVE) {
                drnModelList.add(drone);
            }
        }
        return drnModelList;
    }
}
