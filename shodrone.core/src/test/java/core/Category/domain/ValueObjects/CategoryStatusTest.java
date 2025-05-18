package core.Category.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryStatusTest {

    @Test
    public void testValidCategoryStatus() {
        CategoryStatus status = CategoryStatus.ACTIVE;
        assertNotNull(status);
        assertEquals("ACTIVE", status.name());
    }

    @Test
    public void testCategoryStatusEquality() {
        CategoryStatus status1 = CategoryStatus.ACTIVE;
        CategoryStatus status2 = CategoryStatus.ACTIVE;
        assertEquals(status1, status2);
    }

    @Test
    public void testCategoryStatusInequality() {
        CategoryStatus status1 = CategoryStatus.ACTIVE;
        CategoryStatus status2 = CategoryStatus.INACTIVE;
        assertNotEquals(status1, status2);
    }
}