package core.Drone.repositories;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.ValueObjects.ModelName;

public interface DroneRepository {
    boolean addDrone(SerialNumber serialNumber, ModelName modelName);

    boolean removeDrone(SerialNumber serialNumber, String removReason);



}
