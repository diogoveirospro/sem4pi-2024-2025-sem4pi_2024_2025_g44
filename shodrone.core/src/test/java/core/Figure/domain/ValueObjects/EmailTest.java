package core.Figure.domain.ValueObjects;

import core.Shared.domain.ValueObjects.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void ensureEmailIsCreatedSuccessfully() {
        Email email = new Email("valid.email@example.com");
        assertNotNull(email);
        assertEquals("valid.email@example.com", email.toString());
    }

    @Test
    void ensureEmailValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Email(""));
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
        assertThrows(IllegalArgumentException.class, () -> new Email("invalid-email"));
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        Email email1 = new Email("same.email@example.com");
        Email email2 = new Email("same.email@example.com");
        Email email3 = new Email("different.email@example.com");

        assertEquals(email1, email2);
        assertNotEquals(email1, email3);
    }

    @Test
    void ensureToStringReturnsCorrectValue() {
        Email email = new Email("test.email@example.com");
        assertEquals("test.email@example.com", email.toString());
    }
}