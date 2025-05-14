package jpa;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Category.domain.ValueObjects.CategoryStatus;
import core.Category.repositories.CategoryRepository;
import core.Persistence.Application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JPA implementation of the Category repository.
 */
public class JpaCategoryRepository extends JpaAutoTxRepository<Category, Long, Long> implements CategoryRepository {

    /**
     * Constructor for JPA Category Repository.
     *
     * @param autoTx the transactional context
     */
    public JpaCategoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * Constructor for JPA Category Repository.
     *
     * @param persistenceUnitName the name of the persistence unit
     */
    public JpaCategoryRepository(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), "id");
    }

    /**
     * Returns a list of all categories in the system with the given name.
     *
     * @param categoryName the name of the category to search for
     * @return a list of categories
     */
    @Override
    public Optional<Category> findByName(CategoryName categoryName) {
        List<Category> category = new ArrayList<>();
        for (Category c : findAll()) {
            if (c.name().equals(categoryName)) {
                category.add(c);
            }
        }
        if (category.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(category.get(0));
        }
    }

    /**
     * Returns a list of all active categories in the system.
     * @return a list of active categories
     */
    @Override
    public Iterable<Category> findAllActiveCategories() {
        Iterable<Category> categories = findAll();
        List<Category> activeCategories = new ArrayList<>();

        for (Category c : categories) {
            if (c.status() == CategoryStatus.ACTIVE) {
                activeCategories.add(c);
            }
        }

        return activeCategories;
    }
}
