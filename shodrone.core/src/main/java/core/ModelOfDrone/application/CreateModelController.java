package core.ModelOfDrone.application;

import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.*;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.*;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.validations.Preconditions;

public class CreateModelController {
    private final ModelRepository modelRepository = PersistenceContext.repositories().models();

    public boolean createModel(Name modelName, WindTolerance windTolerance, WindSpeed windSpeed,
                               PositionTolerance posTolerance, SafetyStatus safetyStatus){
        Model model = new Model(modelName,windTolerance, windSpeed, posTolerance, safetyStatus);
        Preconditions.noneNull(model);

        modelRepository.save(model);
        return true;
    }
}
