package inMemory;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Category.repositories.CategoryRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;


public class InMemoryCategoryRepository extends InMemoryDomainRepository<Category, Long> implements CategoryRepository {
    /**
     * Constructor for InMemoryCategoryRepository.
     */
    static {
        InMemoryInitializer.init();
    }

    /**
     * Returns a list of all categories in the system.
     *
     * @return a list of categories
     */
    @Override
    public List<Category> getCategories() {
        return (List<Category>) findAll();
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
}
