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

    @Override
    public Iterable<Drone> findAllDronesInventory() {
        Iterable<Drone> drones = findAll();
        List<Drone> droneList = new ArrayList<>();

        for (Drone drone : drones){
            if (drone.droneStatus().equals(DroneStatus.ACTIVE)) {
                droneList.add(drone);
            }
        }
        return droneList;
    }

    @Override
    public List<Drone> getDrnModelList(Model droneModel){
        List<Drone> drnModelList = new ArrayList<>();
        Iterable<Drone> drones = findAll();
        for (Drone drone: drones){
            if ((drone.model().sameAs(droneModel.identity())) && (drone.droneStatus() == DroneStatus.ACTIVE)){
                drnModelList.add(drone);
            }
        }
        return drnModelList;
    }

}

