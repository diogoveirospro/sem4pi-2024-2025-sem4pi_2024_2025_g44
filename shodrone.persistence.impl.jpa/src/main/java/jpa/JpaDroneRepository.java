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
import jakarta.persistence.TypedQuery;

import java.util.*;


public class JpaDroneRepository extends JpaAutoTxRepository<Drone, Designation, Designation> implements DroneRepository {

    public JpaDroneRepository(TransactionalContext autoTx) {
        super(autoTx, "modelName");

    }

    public JpaDroneRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "modelName");
    }


    //US241
    @Override
    public boolean addDrone(SerialNumber serialNumber, Model model) {
        if (!validateDrone(serialNumber)) {
            return false;
        }
        Map<Date, String> mapRemovalReason = new HashMap<>();
        String startingMesg = "Drone Added";
        mapRemovalReason.put(new Date(), startingMesg);
        RemovalReason removalReason = new RemovalReason(mapRemovalReason);
        Drone drone = new Drone(serialNumber, model, removalReason, DroneStatus.ACTIVE);
        save(drone);
        return true;
    }

    private boolean validateDrone(SerialNumber serialNumber) {
        for (Drone drone : findAll()) {
            if (drone.identity().equals(serialNumber) && drone.getDroneStatus() == DroneStatus.ACTIVE) {
                return false;
            }
        }
        return true;
    }


    //---------------------------------------------------------------------


    //US242
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

    public boolean validateRemoval(Drone drone, Iterable<Drone> drones) {
        for ( Drone droneTest : drones) {
            if (drone.sameAs(droneTest) && drone.getDroneStatus().equals(DroneStatus.ACTIVE)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterable<Drone> findAllDronesInventory() {
        Iterable<Drone> drones = findAll();
        List<Drone> droneList = new ArrayList<>();

        for (Drone drone : drones){
            if (drone.getDroneStatus().equals(DroneStatus.ACTIVE)) {
                droneList.add(drone);
            }
        }
        return droneList;
    }

    public void addDrnRemovData(Drone drone, String removReason) {
        drone.removalReason().addReason(removReason);
        save(drone);
    }

    public void changeDrnStatRemv(Drone drone) {
        drone.setStatus(DroneStatus.REMOVED);
        save(drone);
    }

    //US243
    @Override
    public List<Drone> getDrnModelList(Model droneModel) {
        List<Drone> drnModelList = new ArrayList<>();
        for (Drone drone : findAll()) {
            if (drone.getModel().sameAs(droneModel) && drone.getDroneStatus() == DroneStatus.ACTIVE) {
                drnModelList.add(drone);
            }
        }
        return drnModelList;
    }



    //----------------------------------------------------------------------
}
