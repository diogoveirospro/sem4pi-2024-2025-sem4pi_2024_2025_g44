package shodrone.bootstrappers.Demo.Backoffice;

import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.UsersBootstrapperBase;
import shodrone.presentation.UtilsUI;

/**
 * Bootstrapper for preloading maintenance types in the system.
 */
public class MaintenanceTypeBootstrapper extends UsersBootstrapperBase implements Action {

    private final MaintenanceTypeRepository repo = PersistenceContext.repositories().maintenanceTypes();
    private static final Logger LOGGER = LogManager.getLogger(MaintenanceTypeBootstrapper.class);


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
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Maintenance Type {}" + UtilsUI.RESET,
                maintenanceTypeName.toString());
        if (repo.findByName(maintenanceTypeName).isEmpty()) {
            repo.save(new MaintenanceType(maintenanceTypeName, true));
        }
    }
}