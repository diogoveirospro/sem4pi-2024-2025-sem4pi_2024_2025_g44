package core.Maintenance.repositories;

import core.Drone.domain.Entities.Drone;
import core.Maintenance.domain.Entities.Maintenance;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import eapli.framework.domain.repositories.DomainRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MaintenanceTypeRepository extends DomainRepository<Long, MaintenanceType> {
    Optional<MaintenanceType> findByName(String name);
}