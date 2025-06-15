package core.ModelOfDrone.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.repositories.DroneRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class ListDronesOverTimeLimitController {
    private final DroneRepository droneRepository = PersistenceContext.repositories().drone();

    public Iterable<Drone> listDronesOverTimeLimit() {
        return droneRepository.findDronesOverTimeLimit();
    }
}