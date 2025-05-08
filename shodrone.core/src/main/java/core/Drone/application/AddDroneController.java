package core.Drone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;

public class AddDroneController {

    private ModelRepository modelRepository;
    private DroneRepository droneRepository;

    public AddDroneController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
        this.droneRepository = droneRepository;
    }

    public ModelRepository getModelRepository() {
        return modelRepository= PersistenceContext.repositories().models();
    }

    public DroneRepository getDroneRepository() {
        return droneRepository= PersistenceContext.repositories().drone();
    }

    public boolean verifyModel(ModelName modelName) {
        return getModelRepository().verifyModel(modelName);
    }

    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        return getDroneRepository().addDrone(serialNumber, modelName);
    }
}
