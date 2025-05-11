package inMemory;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;
import org.springframework.boot.Banner;

import java.util.ArrayList;
import java.util.List;

public class InMemoryModelRepository extends InMemoryDomainRepository<Model, Designation> implements ModelRepository {

    static {
        InMemoryInitializer.init();
    }

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
