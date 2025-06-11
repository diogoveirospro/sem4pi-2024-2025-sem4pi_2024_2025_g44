package core.Maintenance.repositories;

import core.Drone.domain.Entities.Drone;
import core.Maintenance.domain.Entities.Maintenance;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MaintenanceTypeRepository extends DomainRepository<Designation, MaintenanceType> {
    Optional<MaintenanceType> findByName(Name name);
}