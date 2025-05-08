package inMemory;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.ValueObjects.ModelName;

public class InMemoryDroneRepository implements DroneRepository {
    @Override
    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        return false;
    }

    @Override
    public boolean validateDrone(SerialNumber serialNumber) {
        return false;
    }
}
