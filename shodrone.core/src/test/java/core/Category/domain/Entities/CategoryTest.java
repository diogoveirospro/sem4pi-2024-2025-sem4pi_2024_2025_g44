package core.Category.domain.Entities;

import core.Category.domain.ValueObjects.CategoryName;
import core.Shared.domain.ValueObjects.Description;
import core.Category.domain.ValueObjects.CategoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private Set<Category> categories;

    @BeforeEach
    public void setUp() {
        categories = new HashSet<>();
        categories.add(new Category(new CategoryName("Category1"), new Description("Description1")));
        categories.add(new Category(new CategoryName("Category2"), new Description("Description2")));
        categories.add(new Category(new CategoryName("Category3"), new Description("Description3")));
    }

    @Test
    public void testCategoryCreation() {
        Category category = new Category(new CategoryName("Category4"), new Description("Description4"));
        assertNotNull(category);
        assertEquals("Category4", category.name().toString());
        assertEquals("Description4", category.description().toString());
    }

    @Test
    public void testCategoryEquality() {
        Category category1 = new Category(new CategoryName("Category1"), new Description("Description1"));
        Category category2 = new Category(new CategoryName("Category1"), new Description("Description1"));
        assertEquals(category1, category2);
    }

    @Test
    public void testCategoryInequality() {
        Category category1 = new Category(new CategoryName("Category1"), new Description("Description1"));
        Category category2 = new Category(new CategoryName("Category2"), new Description("Description2"));
        assertNotEquals(category1, category2);
    }

    @Test
    public void testAddCategoryToSet() {
        Category newCategory = new Category(new CategoryName("Category4"), new Description("Description4"));
        assertTrue(categories.add(newCategory));
        assertEquals(4, categories.size());
    }

    @Test
    public void testDuplicateCategoryInSet() {
        Category duplicateCategory = new Category(new CategoryName("Category1"), new Description("Description1"));
        assertFalse(categories.add(duplicateCategory));
        assertEquals(3, categories.size());
    }

    @Test
    public void testCategoryStatus() {
        Category category = new Category(new CategoryName("Category5"), new Description("Description5"));
        category.changeStatus(CategoryStatus.ACTIVE);
        assertEquals(CategoryStatus.ACTIVE, category.status());
    }
}