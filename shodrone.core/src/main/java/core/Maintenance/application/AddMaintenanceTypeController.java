package core.Maintenance.application;

import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class AddMaintenanceTypeController {

    private final MaintenanceTypeRepository maintenanceTypeRepo = PersistenceContext.repositories().maintenanceTypes();

    public void addMaintenanceType(String name, boolean resetUsageTime) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Maintenance type name cannot be null or blank.");
        }

        if (!maintenanceTypeRepo.findByName(new Name (name)).isEmpty()) {
            throw new IllegalArgumentException("Maintenance type with this name already exists.");
        }

        MaintenanceType maintenanceType = new MaintenanceType(new Name(name), resetUsageTime);


        maintenanceTypeRepo.save(maintenanceType);
    }
}