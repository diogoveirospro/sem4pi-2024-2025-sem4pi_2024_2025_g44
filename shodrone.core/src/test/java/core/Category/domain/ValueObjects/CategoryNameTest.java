package core.Category.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryNameTest {

    @Test
    public void testValidCategoryName() {
        CategoryName name = new CategoryName("ValidName");
        assertNotNull(name);
        assertEquals("ValidName", name.toString());
    }

    @Test
    public void testInvalidCategoryName() {
        assertThrows(IllegalArgumentException.class, () -> new CategoryName(""));
        assertThrows(IllegalArgumentException.class, () -> new CategoryName(null));
    }

    @Test
    public void testCategoryNameEquality() {
        CategoryName name1 = new CategoryName("Name");
        CategoryName name2 = new CategoryName("Name");
        assertEquals(name1, name2);
    }

    @Test
    public void testCategoryNameInequality() {
        CategoryName name1 = new CategoryName("Name1");
        CategoryName name2 = new CategoryName("Name2");
        assertNotEquals(name1, name2);
    }
}