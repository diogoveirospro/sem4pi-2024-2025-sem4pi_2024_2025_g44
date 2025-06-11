package core.Drone.application;

import core.Drone.domain.Entities.Drone;

import core.Drone.repositories.DroneRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.time.LocalTime;

@UseCaseController
public class RegisterUsageTimeController {

    private final DroneRepository droneRepository = PersistenceContext.repositories().drone();

    public void registerUsageTime(Drone drone, LocalTime usageTime){
        if (drone == null || usageTime == null) {
            throw new IllegalArgumentException("Drone and usage time must not be null");
        }
        drone.addUsageTime(usageTime);
        droneRepository.save(drone);
    }

    public Iterable<Drone> findAllDronesInventory () {
        return droneRepository.findAllDronesInventory();
    }
}