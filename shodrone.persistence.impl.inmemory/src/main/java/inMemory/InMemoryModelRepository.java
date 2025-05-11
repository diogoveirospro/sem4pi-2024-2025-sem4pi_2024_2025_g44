package inMemory;

import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.*;
import core.ModelOfDrone.repositories.ModelRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryModelRepository extends InMemoryDomainRepository<Model, Designation> implements ModelRepository {

    static {
        InMemoryInitializer.init();
    }


    //US240
    @Override
    public boolean createModel(ModelName modelName, Map<Double, int[]> config) {
       if (!validateModel(modelName)){ return false;}
       /*if ()
        Model model = new Model(modelName, windTolerance, windSpeed, posTolerance, safetyStatus);
        save(model);*/
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
        Iterable<Model> models = findAll();

        for (Model model : models){
            if (model.sameAs(modelName)){
                return true;
            }
        }
        return false;
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
