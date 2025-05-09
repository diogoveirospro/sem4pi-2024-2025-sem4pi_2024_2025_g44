package core.Drone.application;

import core.Drone.domain.Entities.Drone;
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

    private ModelRepository modelRepository = PersistenceContext.repositories().models();
    private DroneRepository droneRepository = PersistenceContext.repositories().drone();

    public ListDroneController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
        this.droneRepository = droneRepository;
    }

    public ModelRepository getModelRepository() {
        return modelRepository;
    }

    public DroneRepository getDroneRepository() {
        return droneRepository;
    }

    public List<Model> getModelList() {
        return modelRepository.getModelList();
    }

    public List<Drone> getDrnModelList(Model droneModel) {
        return droneRepository.getDrnModelList(droneModel);
    }
}
