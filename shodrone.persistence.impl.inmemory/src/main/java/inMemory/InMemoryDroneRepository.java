package inMemory;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.ValueObjects.ModelID;

public class InMemoryDroneRepository implements DroneRepository {
    @Override
    public boolean addDrone(SerialNumber serialNumber, ModelID modelId) {
        return false;
    }

    @Override
    public boolean validateDrone(SerialNumber serialNumber) {
        return false;
    }
}
