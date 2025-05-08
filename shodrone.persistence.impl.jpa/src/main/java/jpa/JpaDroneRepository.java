package jpa;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaDroneRepository extends JpaAutoTxRepository<Model, Designation, Designation> implements DroneRepository {


    public JpaDroneRepository(TransactionalContext autoTx) {
        super(autoTx, "modelName");
    }

    public JpaDroneRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "modelName");
    }

    @Override
    public boolean addDrone(SerialNumber serialNumber, ModelName modelName) {
        if (!validateDrone(serialNumber)) {
            return false;
        }

        Drone drone = new Drone(serialNumber, DroneStatus.ACTIVE , modelName);
        entityManager().persist(drone);
        return true;    }

    @Override
    public boolean validateDrone(SerialNumber serialNumber) {
        Drone existing = entityManager().createQuery(
                        "SELECT d FROM Drone d WHERE d.serialNumber.value = :sn", Drone.class)
                .setParameter("sn", serialNumber.getValue())
                .getResultStream()
                .findFirst()
                .orElse(null);
        return existing == null;
    }
}
