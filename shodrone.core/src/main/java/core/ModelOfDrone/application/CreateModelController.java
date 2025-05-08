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
    private final ModelRepository modelRepository = PersistenceContext.repositories().models();
    private final DroneRepository droneRepository = PersistenceContext.repositories().drone();


    public boolean createModel(Name modelName, WindTolerance windTolerance, WindSpeed windSpeed,
                               PositionTolerance posTolerance, SafetyStatus safetyStatus){
        Model model = new Model(modelName,windTolerance, windSpeed, posTolerance, safetyStatus);
        Preconditions.noneNull(model);

        modelRepository.save(model);
        return true;
    }

    public boolean verifyModel(ModelID modelId) {
        return modelRepository.verifyModel(modelId);
    }

    public boolean addDrone(SerialNumber serialNumber, ModelID modelId) {
        DroneRepository droneRepo = PersistenceContext.repositories().drone();
        return droneRepo.addDrone(serialNumber, modelId);
    }
}
