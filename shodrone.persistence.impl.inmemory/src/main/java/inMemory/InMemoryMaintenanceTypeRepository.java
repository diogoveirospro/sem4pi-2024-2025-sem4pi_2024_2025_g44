package inMemory;

import core.Maintenance.domain.Entities.MaintenanceType;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.Optional;

public class InMemoryMaintenanceTypeRepository extends InMemoryDomainRepository<MaintenanceType, Designation> implements MaintenanceTypeRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<MaintenanceType> findByName(Name name) {
        return matchOne(e -> e.name().equals(name));
    }
}
