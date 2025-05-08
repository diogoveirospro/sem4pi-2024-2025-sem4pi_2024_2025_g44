package core.Drone.repositories;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.ValueObjects.ModelName;

public interface DroneRepository {
    boolean addDrone(SerialNumber serialNumber, ModelName modelName);

    boolean validateDrone(SerialNumber serialNumber);

}
