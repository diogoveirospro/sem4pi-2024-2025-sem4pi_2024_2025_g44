package core.Maintenance.application;

import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class ListAllMaintenanceTypeController {

    private final MaintenanceTypeRepository maintenancetypeRepo = PersistenceContext.repositories().maintenanceTypes();

    public Iterable<MaintenanceType> findAllMaintenanceTypes() {
        return maintenancetypeRepo.findAll();
    }
}