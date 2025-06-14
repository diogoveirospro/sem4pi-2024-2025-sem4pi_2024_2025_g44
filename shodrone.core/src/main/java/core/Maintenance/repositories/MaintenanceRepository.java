package core.Maintenance.repositories;

import core.Drone.domain.Entities.Drone;
import core.Maintenance.domain.Entities.Maintenance;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import core.Maintenance.domain.Entities.MaintenanceType;
import eapli.framework.domain.repositories.DomainRepository;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRepository extends DomainRepository<Long, Maintenance> {
    List<Maintenance> findByDrone(Drone drone);

    List<Maintenance> findByDroneAndDateBetween(Drone drone, LocalDate startDate, LocalDate endDate);

    Iterable<Drone> findAllWithMaintenance();

    List<Maintenance> findByType(MaintenanceType type);
}
