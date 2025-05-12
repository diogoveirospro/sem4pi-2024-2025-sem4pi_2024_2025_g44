package jpa;

import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JpaModelRepository extends JpaAutoTxRepository<Model, Designation, Designation> implements ModelRepository {


    public JpaModelRepository(TransactionalContext autoTx) {
        super(autoTx, "modelName");
    }

    public JpaModelRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "modelName");
    }

    //US240
    @Override
    public boolean createModel(ModelName modelName, Configuration config) {
        if (!validateModel(modelName)){ return false;}
        Model model = new Model(modelName, config);
        save(model);
        return true;
    }

    @Override
    public boolean validateModel(ModelName modelName) {
        Iterable<Model> models = findAll();

        for (Model model : models){
            if (model.sameAs(modelName)){
                return false;
            }
        }
        return true;
    }

    //----------------------------------------------------------------------


    //US241
    @Override
    public boolean verifyModel(ModelName modelName) {
        return entityManager().find(Drone.class, modelName) != null;
    }
    //----------------------------------------------------------------------


    //US243
    @Override
    public List<Model> getModelList() {

        Iterable<Model> models = findAll();
        List<Model> modelList = new ArrayList<>();

        for (Model model : models){
            modelList.add(model);
        }
        return modelList;
    }
    //----------------------------------------------------------------------

    @Override
    public Iterable<Model> findAllModels() {
        Iterable<Model> models = findAll();
        List<Model> result = new ArrayList<>();
        for (Model model : models) {
            result.add(model);
        }
        return result;
    }
}
