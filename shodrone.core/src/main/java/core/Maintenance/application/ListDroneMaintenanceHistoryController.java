package core.Maintenance.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.repositories.DroneRepository;
import core.Maintenance.domain.Entities.Maintenance;
import core.Maintenance.repositories.MaintenanceRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.time.LocalDate;
import java.util.List;

@UseCaseController
public class ListDroneMaintenanceHistoryController {

    private final MaintenanceRepository repo = PersistenceContext.repositories().maintenance();
    private final DroneRepository droneRepository = PersistenceContext.repositories().drone();

    public List<Maintenance> historyForDroneBetween(Drone drone, LocalDate from, LocalDate to) {
        return repo.findByDroneAndDateBetween(drone, from, to);
    }
    public Iterable<Drone> findAllDronesInventory () {
        return droneRepository.findAllDronesInventory();
    }
}