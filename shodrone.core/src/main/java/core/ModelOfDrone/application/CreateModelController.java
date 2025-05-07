package core.ModelOfDrone.application;

import core.ModelOfDrone.domain.ValueObjects.*;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.*;

public class CreateModelController {
    private final ModelRepository modelRepository;
    public CreateModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;

    }
    public ModelRepository getModelRepository(){
        return PersistenceContext.RepositoryFactory.getInstance().getModelRepository;
    }
    public boolean createModel(WindTolerance windTolerance, WindSpeed windSpeed,
                               PositionTolerance posTolerance, SafetyStatus safetyStatus){
        ModelID id = modelRepository.getNextId();
        Model model = new Model(id, windTolerance, windSpeed, posTolerance, safetyStatus);
        if (modelRepository.validateModel(model)) {
            return modelRepository.save(model);
        }
        return false;
    }
}
