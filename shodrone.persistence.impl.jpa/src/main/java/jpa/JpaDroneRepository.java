package jpa;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.ArrayList;
import java.util.List;

public class JpaDroneRepository extends JpaAutoTxRepository<Drone, Designation, Designation> implements DroneRepository {


    public JpaDroneRepository(TransactionalContext autoTx) {
        super(autoTx, "modelName");
    }

    public JpaDroneRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "modelName");
    }


    //US241
    @Override
    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        if (!validateDrone(serialNumber)) {
            return false;
        }

        Drone drone = new Drone(serialNumber, modelName, null);
        save(drone);
        return true;
    }

    private boolean validateDrone(SerialNumber serialNumber) {
        return entityManager().find(Drone.class, serialNumber) == null;
    }


    //---------------------------------------------------------------------


    //US242
    @Override
    public boolean removeDrone(SerialNumber serialNumber, String removReason) {
        if (!validateRemoval(serialNumber)) {
            return false;
        }
        Drone drone = entityManager().find(Drone.class, serialNumber);
        addDrnRemovData(serialNumber, removReason);
        changeDrnStatRemv(drone);
        return true;
    }

    public boolean validateRemoval(SerialNumber serialNumber) {
        return entityManager().find(Drone.class, serialNumber) != null;
    }

    public void addDrnRemovData(SerialNumber serialNumber, String removReason) {
        Drone drone = entityManager().find(Drone.class, serialNumber);
        if (drone != null) {
            drone.removalReason().addReason(removReason);
            entityManager().merge(drone);
        }
    }

    public void changeDrnStatRemv(Drone drone) {
        drone.setStatus(DroneStatus.REMOVED);
        save(drone);
    }

    //US243
    @Override
    public List<Drone> getDrnModelList(Model droneModel){
        List<Drone> drnModelList = new ArrayList<>();
        Iterable<Drone> drones = findAll();
        for (Drone drone: drones){
            if (drone.getModelName() == droneModel.identity()){
                drnModelList.add(drone);
            }
        }
        return drnModelList;
    }

    //----------------------------------------------------------------------
}
