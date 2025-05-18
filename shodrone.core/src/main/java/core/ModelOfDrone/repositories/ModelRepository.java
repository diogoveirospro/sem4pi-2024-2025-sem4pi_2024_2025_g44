package core.ModelOfDrone.repositories;


import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.*;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

import java.util.List;
import java.util.Map;

public interface ModelRepository extends DomainRepository<Designation, Model> {
    boolean validateModel(ModelName modelName);

    Iterable<Model> findAllModels();

    boolean createModel(ModelName modelName, Configuration config);

}
