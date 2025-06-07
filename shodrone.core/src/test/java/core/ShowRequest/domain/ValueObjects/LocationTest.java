package core.ShowRequest.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void ensureLocationIsCreatedSuccessfully() {
        Location location = new Location(38.7169, -9.139, 15.0); // Example: Lisbon coordinates
        assertNotNull(location);
        assertEquals("38.7169, -9.139, 15.0", location.toString());
    }

    @Test
    void ensureLocationValidationFailsForInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> new Location(-91, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> new Location(91, 0, 10));
    }

    @Test
    void ensureLocationValidationFailsForInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> new Location(0, -181, 10));
        assertThrows(IllegalArgumentException.class, () -> new Location(0, 181, 10));
    }

    @Test
    void ensureLocationValidationFailsForInvalidAltitude() {
        assertThrows(IllegalArgumentException.class, () -> new Location(0, 0, -1));
    }

    @Test
    void ensureLocationEqualsWorksCorrectly() {
        Location location1 = new Location(38.7169, -9.139, 15.0);
        Location location2 = new Location(38.7169, -9.139, 15.0);
        Location location3 = new Location(40.7128, -74.006, 10.0); // Example: New York City coordinates

        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
    }

    @Test
    void ensureLocationToStringReturnsCorrectValue() {
        Location location = new Location(38.7169, -9.139, 15.0);
        assertEquals("38.7169, -9.139, 15.0", location.toString());
    }
}