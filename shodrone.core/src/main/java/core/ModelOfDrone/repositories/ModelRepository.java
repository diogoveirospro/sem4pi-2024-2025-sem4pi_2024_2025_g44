package core.ModelOfDrone.repositories;


import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

public interface ModelRepository extends DomainRepository<Designation, Model> {
    boolean verifyModel(ModelName modelName);


}
