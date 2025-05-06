package core.Shared.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    void testValueOf_ValidInput() {
        Description description = Description.valueOf("Valid Description");
        assertEquals("Valid Description", description.toString());
    }

    @Test
    void testValueOf_NullInput_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> Description.valueOf(null));
    }

    @Test
    void testValueOf_EmptyInput_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> Description.valueOf(""));
    }

    @Test
    void testEquals_SameValue() {
        Description d1 = Description.valueOf("Test");
        Description d2 = Description.valueOf("Test");
        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testEquals_DifferentValue() {
        Description d1 = Description.valueOf("Test 1");
        Description d2 = Description.valueOf("Test 2");
        assertNotEquals(d1, d2);
    }

    @Test
    void testLength() {
        Description description = Description.valueOf("123456");
        assertEquals(6, description.length());
    }

    @Test
    void testToString() {
        Description description = Description.valueOf("Anything");
        assertEquals("Anything", description.toString());
    }

}
