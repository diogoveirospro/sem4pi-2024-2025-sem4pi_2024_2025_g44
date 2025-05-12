package inMemory;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

public class InMemoryDroneRepository extends InMemoryDomainRepository<Drone, Designation> implements DroneRepository {

    //US241
    @Override
    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        if (!validateDrone(serialNumber)) {
            return false;
        }

        Drone drone = new Drone(serialNumber, modelName, null);
        save(drone);
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
    public boolean removeDrone(SerialNumber serialNumber, String removReason) {
        if (!validateRemoval(serialNumber)) {
            return false;
        }
        addDrnRemovData(serialNumber, removReason);
        changeDrnStatRemv(serialNumber);
        return true;
    }

    public boolean validateRemoval(SerialNumber serialNumber) {
        Iterable<Drone> drones = findAll();
        for (Drone drone : drones) {
            if (drone.identity() == serialNumber) {
                return true;
            }
        }
        return false;
    }

    public void addDrnRemovData(SerialNumber serialNumber, String removReason) {
        Iterable<Drone> drones = findAll();
        for (Drone drone : drones) {
            if (drone.identity() == serialNumber) {
                drone.removalReason().addReason(removReason);
                save(drone);
                break;
            }
        }

    }

    public void changeDrnStatRemv(SerialNumber serialNumber) {
        Iterable<Drone> drones = findAll();
        for (Drone drone : drones) {
            if (drone.identity() == serialNumber) {
                drone.setStatus(DroneStatus.REMOVED);
                save(drone);
                break;
            }
        }
    }
    //----------------------------------------------------------------------


    //US243
    @Override
    public List<Drone> getDrnModelList(Model droneModel){
        List<Drone> drnModelList = new ArrayList<>();
        Iterable<Drone> drones = findAll();
        for (Drone drone: drones){
            if ((drone.getModelName() == droneModel.identity()) && (drone.getDroneStatus() == DroneStatus.ACTIVE)){
                drnModelList.add(drone);
            }
        }
        return drnModelList;
    }

    //----------------------------------------------------------------------


}

