package inMemory;

import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.ValueObjects.ModelName;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDroneRepository implements DroneRepository {
    private final Map<SerialNumber, Map<DroneStatus ,ModelName>> storage = new ConcurrentHashMap<>();

    @Override
    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        if (!validateDrone(serialNumber)) {
            return false;
        }
        Map<DroneStatus, ModelName> droneInfo = new ConcurrentHashMap<>();
        droneInfo.put(DroneStatus.ACTIVE ,modelName);

        storage.put(serialNumber, droneInfo);
        return true;
    }

    public boolean validateDrone(SerialNumber serialNumber) {
        return !storage.containsKey(serialNumber);
    }
}
