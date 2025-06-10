package jpa;

import core.Drone.domain.Entities.Drone;
import core.Maintenance.domain.Entities.Maintenance;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class JpaMaintenanceRepository extends JpaAutoTxRepository<Maintenance, MaintenanceID, MaintenanceID>
        implements MaintenanceRepository {

    public JpaMaintenanceRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    @Override
    public List<Maintenance> findByDrone(Drone drone) {
        final TypedQuery<Maintenance> query = entityManager().createQuery(
                "SELECT m FROM Maintenance m WHERE m.drone = :drone", Maintenance.class);
        query.setParameter("drone", drone);
        return query.getResultList();
    }

    @Override
    public List<Maintenance> findByDroneAndDateBetween(Drone drone, LocalDate startDate, LocalDate endDate) {
        final TypedQuery<Maintenance> query = entityManager().createQuery(
                "SELECT m FROM Maintenance m WHERE m.drone = :drone AND m.date.value BETWEEN :start AND :end", Maintenance.class);
        query.setParameter("drone", drone);
        query.setParameter("start", startDate);
        query.setParameter("end", endDate);
        return query.getResultList();
    }

    @Override
    public List<Maintenance> findByType(MaintenanceType type) {
        final TypedQuery<Maintenance> query = entityManager().createQuery(
                "SELECT m FROM Maintenance m WHERE m.type = :type", Maintenance.class);
        query.setParameter("type", type);
        return query.getResultList();
    }
}