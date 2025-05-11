package core.ModelOfDrone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.*;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.*;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.validations.Preconditions;

import java.util.Map;

public class CreateModelController {
    private ModelRepository modelRepository = PersistenceContext.repositories().models();

    public CreateModelController() {
    }

    public boolean createModel(ModelName modelName, Map<Double, int[]> config){
        return modelRepository.createModel(modelName, config);
    }

}
