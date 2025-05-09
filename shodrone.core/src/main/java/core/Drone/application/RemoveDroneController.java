package core.Drone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController

public class RemoveDroneController {
    private DroneRepository droneRepository= PersistenceContext.repositories().drone();

    public RemoveDroneController(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }


    public boolean removeDrone(SerialNumber serialNumber, String removReason) {
        return droneRepository.removeDrone(serialNumber, removReason);
    }
}
