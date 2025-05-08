package inMemory;

import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

public class InMemoryModelRepository extends InMemoryDomainRepository<Model, Designation> implements ModelRepository {

    static {
        InMemoryInitializer.init();
    }


    @Override
    public boolean verifyModel(ModelName modelName) {
        return false;
    }
}
