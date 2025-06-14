package core.Maintenance.domain.Entities;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.Entities.DroneTestHelper;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Name;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceTest {

    private final Drone dummyDrone = DroneTestHelper.createDummyDrone();
    private final MaintenanceType type = new MaintenanceType(new Name("Verification"),true);
    private final Description description = Description.valueOf("Verification after flight");
    private final LocalDate date = LocalDate.now();

    @Test
    void shouldCreateValidMaintenance() {
        Maintenance m = new Maintenance(
               dummyDrone, type, description, date
        );

        assertNotNull(m);
        assertEquals(dummyDrone, m.drone());
        assertEquals(type, m.type());
        assertEquals(description, m.description());
        assertEquals(date, m.date());
    }

    @Test
    void shouldThrowIfAnyArgumentIsNull() {
        MaintenanceID id = MaintenanceID.newID();

        assertThrows(IllegalArgumentException.class, () -> new Maintenance(null, type, description, date));
        assertThrows(IllegalArgumentException.class, () -> new Maintenance(dummyDrone, null, description, date));
        assertThrows(IllegalArgumentException.class, () -> new Maintenance( dummyDrone, type, null, date));
        assertThrows(IllegalArgumentException.class, () -> new Maintenance( dummyDrone, type, description, null));
    }

    @Test
    void shouldBeSameAsIfSameID() {
        MaintenanceID id = MaintenanceID.newID();

        Maintenance m1 = new Maintenance(dummyDrone, type, description, date);
        Maintenance m2 = new Maintenance(dummyDrone, type, description, date);

        m1.setId(1L);
        m2.setId(1L);
        assertTrue(m1.sameAs(m2));
    }

    @Test
    void shouldNotBeSameAsIfDifferentID() {
        Maintenance m1 = new Maintenance( dummyDrone, type, description, date);
        Maintenance m2 = new Maintenance(dummyDrone, type, description, date);

        m1.setId(1L);
        m2.setId(2L);
        assertFalse(m1.sameAs(m2));
    }
}