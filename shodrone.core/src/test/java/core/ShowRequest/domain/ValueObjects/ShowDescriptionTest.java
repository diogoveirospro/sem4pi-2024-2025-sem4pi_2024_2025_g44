package core.ShowRequest.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowDescriptionTest {

    @Test
    void ensureShowDescriptionIsCreatedSuccessfully() {
        ShowDescription description = new ShowDescription("ShowDescription");
        assertNotNull(description);
        assertEquals("ShowDescription", description.toString());
    }

    @Test
    void ensureShowDescriptionValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new ShowDescription(null));
    }

    @Test
    void ensureShowDescriptionEqualsWorksCorrectly() {
        ShowDescription description1 = new ShowDescription("ShowDescription");
        ShowDescription description2 = new ShowDescription("ShowDescription");
        ShowDescription description3 = new ShowDescription("AHMMM");

        assertEquals(description1, description2);
        assertNotEquals(description1, description3);
    }

    @Test
    void ensureShowDescriptionToStringReturnsCorrectValue() {
        Location location = new Location("ShowDescription");
        assertEquals("ShowDescription", location.toString());
    }
}