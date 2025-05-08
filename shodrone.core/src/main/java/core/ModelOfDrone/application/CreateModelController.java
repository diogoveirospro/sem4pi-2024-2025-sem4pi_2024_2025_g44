package core.ModelOfDrone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.*;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.*;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.validations.Preconditions;

public class CreateModelController {
    private ModelRepository modelRepository;
    private DroneRepository droneRepository;

    public CreateModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
        this.droneRepository = droneRepository;
    }

    public ModelRepository getModelRepository() {
        return modelRepository= PersistenceContext.repositories().models();
    }




    //US240
    public boolean createModel(Name modelName, WindTolerance windTolerance, WindSpeed windSpeed,
                               PositionTolerance posTolerance, SafetyStatus safetyStatus){
        Model model = new Model(modelName,windTolerance, windSpeed, posTolerance, safetyStatus);
        Preconditions.noneNull(model);

        modelRepository.save(model);
        return true;
    }

    //------------------------------------------------------------------
    //US241
    public boolean verifyModel(ModelName modelName) {
        ModelRepository modelRepo = getModelRepository();
        return modelRepo.verifyModel(modelName);
    }

    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        DroneRepository droneRepo = PersistenceContext.repositories().drone();
        return droneRepo.addDrone(serialNumber, modelName);
    }
}
