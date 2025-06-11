package shodrone.bootstrappers;

import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.actions.Action;

/**
 * Bootstrapper for preloading maintenance types in the system.
 */
public class MaintenanceTypeBootstrapper implements Action {

    private final MaintenanceTypeRepository repo = PersistenceContext.repositories().maintenanceTypes();

    @Override
    public boolean execute() {
        addIfNotExists("Battery Check");
        addIfNotExists("Firmware Update");
        addIfNotExists("Motor Inspection");
        addIfNotExists("Sensor Calibration");
        return true;
    }

    private void addIfNotExists(String name) {
        Name maintenanceTypeName = new Name(name);
        if (repo.findByName(maintenanceTypeName).isEmpty()) {
            repo.save(new MaintenanceType(maintenanceTypeName));
        }
    }
}