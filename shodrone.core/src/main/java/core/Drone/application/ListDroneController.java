package core.Drone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class ListDroneController {

    private ModelRepository modelRepository;
    private DroneRepository droneRepository;

    public ListDroneController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
        this.droneRepository = droneRepository;
    }

    public ModelRepository getModelRepository() {
        return modelRepository = PersistenceContext.repositories().models();
    }

    public DroneRepository getDroneRepository() {
        return droneRepository = PersistenceContext.repositories().drone();
    }

    public List<Model> getModelList() {
        return getModelRepository().getModelList();
    }

    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        return getDroneRepository().addDrone(serialNumber, modelName);
    }
}
