package jpa;

import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelID;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaModelRepository extends JpaAutoTxRepository<Model, Designation, Designation> implements ModelRepository {


    public JpaModelRepository(TransactionalContext autoTx) {
        super(autoTx, "modelName");
    }

    public JpaModelRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "modelName");
    }


    @Override
    public boolean verifyModel(ModelID modelId) {
        return false;
    }
}
