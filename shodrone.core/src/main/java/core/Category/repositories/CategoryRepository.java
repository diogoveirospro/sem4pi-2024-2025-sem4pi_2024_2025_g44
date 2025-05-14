package core.Category.repositories;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface CategoryRepository extends DomainRepository<Long, Category> {

    /**
     * Returns a list of all categories in the system with the given name.
     * @param categoryName the name of the category to search for
     * @return a list of categories
     */
    Optional<Category> findByName(CategoryName categoryName);

    /**
     * Returns a list of all active categories in the system.
     * @return a list of active categories
     */
    Iterable<Category> findAllActiveCategories();
}
