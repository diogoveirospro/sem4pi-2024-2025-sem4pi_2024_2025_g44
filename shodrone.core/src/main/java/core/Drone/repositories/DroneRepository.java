package core.Drone.repositories;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.ValueObjects.ModelID;
import core.ModelOfDrone.repositories.ModelRepository;

public interface DroneRepository {
    boolean addDrone(SerialNumber serialNumber, ModelID modelId);

    boolean validateDrone(SerialNumber serialNumber);

}
