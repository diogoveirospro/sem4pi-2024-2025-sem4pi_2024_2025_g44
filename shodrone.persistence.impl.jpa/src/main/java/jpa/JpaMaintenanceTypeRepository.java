package jpa;

import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Persistence.Application;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Optional;

public class JpaMaintenanceTypeRepository
        extends JpaAutoTxRepository<MaintenanceType, Long, Long>
        implements MaintenanceTypeRepository {

    public JpaMaintenanceTypeRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "id");
    }

    @Override
    public Optional<MaintenanceType> findByName(final String name) {
        final var query = entityManager().createQuery(
                "SELECT e FROM MaintenanceType e WHERE e.name = :name", MaintenanceType.class);
        query.setParameter("name", name);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}