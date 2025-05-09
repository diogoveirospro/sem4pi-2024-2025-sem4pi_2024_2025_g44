package core.Drone.repositories;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;

import java.util.List;

public interface DroneRepository {
    boolean addDrone(SerialNumber serialNumber, ModelName modelName);

    boolean removeDrone(SerialNumber serialNumber, String removReason);

    List<Drone> getDrnModelList(Model droneModel);
}
