package core.Drone.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController

public class RemoveDroneController {
    private DroneRepository droneRepository= PersistenceContext.repositories().drone();

    public RemoveDroneController() {
    }


    public boolean removeDrone(Drone drone, String removReason) {
        Iterable<Drone> drones = droneRepository.findAllDronesInventory();

        if (!validateRemoval(drone, drones)) {
            return false;
        }

        addDrnRemovData(drone, removReason);
        changeDrnStatRemv(drone);
        droneRepository.save(drone);
        return true;
    }

    public boolean validateRemoval(Drone drone, Iterable<Drone> drones) {
        for (Drone droneTest : drones) {
            if (drone.equals(droneTest) && drone.droneStatus().equals(DroneStatus.ACTIVE)) {
                return true;
            }
        }
        return false;
    }

    public void addDrnRemovData(Drone drone, String removReason) {
        drone.removalReason().addReason(removReason);
    }

    public void changeDrnStatRemv(Drone drone) {
        drone.setDroneStatus(DroneStatus.REMOVED);
    }

    public Iterable<Drone> listDrones() {
        return droneRepository.findAllDronesInventory();
    }

}
