package core.Shared.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void ensureNameIsCreatedSuccessfully() {
        Name name = new Name("Valid Name");
        assertNotNull(name);
        assertEquals("Valid Name", name.toString());
    }

    @Test
    void ensureNameValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Name(""));
        assertThrows(IllegalArgumentException.class, () -> new Name(null));
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        Name name1 = new Name("Same Name");
        Name name2 = new Name("Same Name");
        Name name3 = new Name("Different Name");

        assertEquals(name1, name2);
        assertNotEquals(name1, name3);
    }

    @Test
    void ensureToStringReturnsCorrectValue() {
        Name name = new Name("Test Name");
        assertEquals("Test Name", name.toString());
    }
}