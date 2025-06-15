package shodrone.bootstrappers.Demo.Backoffice;

import core.Drone.domain.Entities.Drone;
import core.Drone.repositories.DroneRepository;
import core.Maintenance.domain.Entities.Maintenance;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import core.Maintenance.repositories.MaintenanceRepository;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Date;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.UsersBootstrapperBase;
import shodrone.presentation.UtilsUI;

import java.time.LocalDate;


public class MaintenanceBootstrapper extends UsersBootstrapperBase implements Action {

    private static final Logger LOGGER = LogManager.getLogger(MaintenanceBootstrapper.class);
    private final MaintenanceRepository maintenanceRepo = PersistenceContext.repositories().maintenance();
    private final MaintenanceTypeRepository typeRepo = PersistenceContext.repositories().maintenanceTypes();

    @Override
    public boolean execute() {
        Iterable<Drone> drones = PersistenceContext.repositories().drone().findAll();

        if (!drones.iterator().hasNext()) {
            LOGGER.warn("No drones found. Skipping maintenance bootstrap.");
            return true;
        }

        Name name = new Name("Battery Check");
        Drone drone = drones.iterator().next();
        MaintenanceType type = typeRepo.findByName(name).orElse(null);

        if (type == null) {
            LOGGER.warn("No maintenance type 'Battery Check' found. Skipping maintenance bootstrap.");
            return true;
        }

        register(drone, type, "Initial battery diagnostic", LocalDate.now().minusDays(10));
        register(drone, type, "Battery replaced", LocalDate.now().minusDays(5));

        return true;
    }

    private void register(Drone drone, MaintenanceType type, String desc, LocalDate date) {

        Maintenance m = new Maintenance(
                drone,
                type,
                new Description(desc),
                date
        );
        maintenanceRepo.save(m);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Inserted maintenance: {} on {}" + UtilsUI.RESET, desc, date);
    }
}
