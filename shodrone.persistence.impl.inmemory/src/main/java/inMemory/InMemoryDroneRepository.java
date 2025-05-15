package inMemory;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;
import org.springframework.boot.Banner;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

public class InMemoryDroneRepository extends InMemoryDomainRepository<Drone, Designation> implements DroneRepository {
    private final InMemoryModelRepository modelRepository;

    public InMemoryDroneRepository() {
        // Inicializa o repositório de modelos em memória
        this.modelRepository = new InMemoryModelRepository();
    }
    //US241
    @Override
    public boolean addDrone(SerialNumber serialNumber, Model model) {
        if (!validateDrone(serialNumber)) {
            return false;
        }

        // Buscar todos os modelos no repositório de Model
        Iterable<Model> models = modelRepository.findAll();  // Obtém todos os modelos em memória
        Model selectedModel = null;

        // Tentar encontrar o modelo correto pelo ModelName
        for (Model modelTest : models) {
            if (modelTest.sameAs(model)) {
                selectedModel = modelTest;
                break;
            }
        }

        // Se não encontrar o modelo, retorna false
        if (selectedModel == null) {
            return false;
        }

        Map <Date, String> mapRemovalReason = new HashMap<>();
        String startingMesg = "Drone Added";
        mapRemovalReason.put(new Date(), startingMesg);
        RemovalReason removalReason = new RemovalReason(mapRemovalReason);
        Drone drone = new Drone(serialNumber, model, removalReason, DroneStatus.ACTIVE);
        save(drone);  // Salva o drone no repositório
        return true;
    }

    public boolean validateDrone(SerialNumber serialNumber) {
        Iterable<Drone> drones = findAll();
        for (Drone drone : drones) {
            if (drone.identity() == serialNumber) {
                return false;
            }
        }
        return true;
    }
    //---------------------------------------------------------------------


    //US242

    @Override
    public boolean removeDrone(Drone drone, String removReason) {

        Iterable<Drone> drones = findAllDronesInventory();

        if (!validateRemoval(drone, drones)) {
            return false;
        }
        addDrnRemovData(drone, removReason);
        changeDrnStatRemv(drone);
        return true;
    }

    public boolean validateRemoval(Drone drone, Iterable<Drone> drones) {
        for ( Drone droneTest : drones) {
            if (drone.sameAs(droneTest) && drone.getDroneStatus().equals(DroneStatus.ACTIVE)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterable<Drone> findAllDronesInventory() {
        Iterable<Drone> drones = findAll();
        List<Drone> droneList = new ArrayList<>();

        for (Drone drone : drones){
            if (drone.getDroneStatus().equals(DroneStatus.ACTIVE)) {
                droneList.add(drone);
            }
        }
        return droneList;
    }

    public void addDrnRemovData(Drone drone, String removReason) {
        drone.removalReason().addReason(removReason);
        save(drone);
    }

    public void changeDrnStatRemv(Drone drone) {
        drone.setStatus(DroneStatus.REMOVED);
        save(drone);
    }
    //----------------------------------------------------------------------


    //US243
    @Override
    public List<Drone> getDrnModelList(Model droneModel){
        List<Drone> drnModelList = new ArrayList<>();
        Iterable<Drone> drones = findAll();
        for (Drone drone: drones){
            if ((drone.getModel().sameAs(droneModel.identity())) && (drone.getDroneStatus() == DroneStatus.ACTIVE)){
                drnModelList.add(drone);
            }
        }
        return drnModelList;
    }

    //----------------------------------------------------------------------


}

