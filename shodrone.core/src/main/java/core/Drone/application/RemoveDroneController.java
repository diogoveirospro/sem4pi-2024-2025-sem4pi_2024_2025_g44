package core.Drone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController

public class RemoveDroneController {
    private DroneRepository droneRepository;

    public RemoveDroneController(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public DroneRepository getDroneRepository() {
        return droneRepository= PersistenceContext.repositories().drone();
    }

    public boolean removeDrone(SerialNumber serialNumber, String removReason) {
        return getDroneRepository().removeDrone(serialNumber, removReason);
    }
}
