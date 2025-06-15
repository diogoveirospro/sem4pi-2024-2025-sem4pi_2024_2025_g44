package core.ModelOfDrone.application;

import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.*;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.*;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.validations.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

public class CreateModelController {
    private ModelRepository modelRepository = PersistenceContext.repositories().models();

    public CreateModelController() {
    }

    public boolean createModel(ModelName modelName, Configuration config, TimeLimit timeLimit) {
        if (!validateModel(modelName)) {
            return false;
        }
        Model model = new Model(modelName, config, timeLimit);
        modelRepository.save(model);
        return true;
    }
    public boolean validateModel(ModelName modelName) {
        for (Model model : modelRepository.findAllModels()) {
            if (model.sameAs(modelName)) {
                return false;
            }
        }
        return true;
    }

}
