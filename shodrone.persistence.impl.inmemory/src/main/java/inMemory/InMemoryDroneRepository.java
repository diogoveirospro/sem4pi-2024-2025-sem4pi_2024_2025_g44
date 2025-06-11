package inMemory;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;


import java.util.*;

public class InMemoryDroneRepository extends InMemoryDomainRepository<Drone, Designation> implements DroneRepository {
    private final InMemoryModelRepository modelRepository;

    public InMemoryDroneRepository() {
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

