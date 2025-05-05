package jpa;

import core.Category.domain.Entities.Category;
import core.Category.repositories.CategoryRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaCategoryRepository extends JpaAutoTxRepository<Category, Long, Long> implements CategoryRepository {

    public JpaCategoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaCategoryRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "id");
    }
}
