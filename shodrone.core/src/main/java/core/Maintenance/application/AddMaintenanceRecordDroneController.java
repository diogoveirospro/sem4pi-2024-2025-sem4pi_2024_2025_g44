package core.Maintenance.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.Maintenance.domain.Entities.Maintenance;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceRepository;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Name;
import core.User.domain.Entities.ShodroneUser;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@UseCaseController
public class AddMaintenanceRecordDroneController {

    private final DroneRepository droneRepo = PersistenceContext.repositories().drone();
    private final MaintenanceRepository maintenanceRepo = PersistenceContext.repositories().maintenance();
    private final MaintenanceTypeRepository typeRepo = PersistenceContext.repositories().maintenanceTypes();

    @Transactional
    public void addMaintenance(Drone drone, MaintenanceType maintenanceType,
                                      LocalDate date, String description) {

        if (drone == null || maintenanceType == null || date == null || description == null) {
            throw new IllegalArgumentException("Drone, Maintenance Type, Date and Description cannot be null.");
        }


        Maintenance maintenance = new Maintenance(drone, maintenanceType ,new Description(description), date);

        //Reset horas

        if(maintenanceType.resetUsageTime()){
            drone.resetUsageTime();
            droneRepo.save(drone);
        }

        maintenanceRepo.save(maintenance);


    }
    public Iterable<Drone> findAllDronesInventory () {
        return droneRepo.findAllDronesInventory();
    }

    public Iterable<MaintenanceType> findAllMaintenanceType () {
        return typeRepo.findAll();
    }
}