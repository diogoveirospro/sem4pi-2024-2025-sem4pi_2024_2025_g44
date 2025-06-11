package core.Drone.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UseCaseController
public class AddDroneController {

    private ModelRepository modelRepository= PersistenceContext.repositories().models();
    private DroneRepository droneRepository= PersistenceContext.repositories().drone();

    public AddDroneController() {
    }
    public boolean addDrone(SerialNumber serialNumber, Model model) {
        if (!validateDrone(serialNumber)) {
            return false;
        }

        Map<Date, String> mapRemovalReason = new HashMap<>();
        String startingMsg = "Drone Added";
        mapRemovalReason.put(new Date(), startingMsg);
        RemovalReason removalReason = new RemovalReason(mapRemovalReason);

        Drone drone = new Drone(serialNumber, model, removalReason, DroneStatus.ACTIVE, null);
        droneRepository.save(drone);
        return true;
    }
    public Iterable<Model> listModels() {
        return modelRepository.findAllModels();
    }

    private boolean validateDrone(SerialNumber serialNumber) {
        for (Drone drone : droneRepository.findAllDronesInventory()) {
            if (drone.identity().equals(serialNumber) && drone.droneStatus() == DroneStatus.ACTIVE) {
                return false;
            }
        }
        return true;
    }

}
