package core.Drone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class AddDroneController {

    private ModelRepository modelRepository= PersistenceContext.repositories().models();
    private DroneRepository droneRepository= PersistenceContext.repositories().drone();

    public AddDroneController() {
    }

    public boolean verifyModel(ModelName modelName) {
        return modelRepository.verifyModel(modelName);
    }

    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        return droneRepository.addDrone(serialNumber, modelName);
    }
}
