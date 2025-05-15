package core.Drone.application;

import core.Drone.domain.Entities.Drone;
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
        return droneRepository.removeDrone(drone, removReason);
    }

    public Iterable<Drone> listDrones() {
        return droneRepository.findAllDronesInventory();
    }

}
