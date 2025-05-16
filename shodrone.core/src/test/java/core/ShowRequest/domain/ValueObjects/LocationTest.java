package core.ShowRequest.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {


    @Test
    void ensureLocationIsCreatedSuccessfully() {
        Location location = new Location("Porto");
        assertNotNull(location);
        assertEquals("Porto", location.toString());
    }

    @Test
    void ensureLocationValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Location(null));
    }

    @Test
    void ensureLocationEqualsWorksCorrectly() {
        Location location1 = new Location("Porto");
        Location location2 = new Location("Porto");
        Location location3 = new Location("Lisbon");

        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
    }

    @Test
    void ensureLocationToStringReturnsCorrectValue() {
        Location location = new Location("Porto");
        assertEquals("Porto", location.toString());
    }

}