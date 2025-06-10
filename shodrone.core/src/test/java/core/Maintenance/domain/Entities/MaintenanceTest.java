package core.Maintenance.domain.Entities;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.Entities.DroneTestHelper;
import core.Maintenance.domain.ValueObjects.MaintenanceID;
import core.Shared.domain.ValueObjects.Date;
import core.Shared.domain.ValueObjects.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceTest {

    private final Drone dummyDrone = DroneTestHelper.createDummyDrone();
    private final MaintenanceType type = new MaintenanceType("Verification");
    private final Description description = Description.valueOf("Verification after flight");
    private final Date date = new Date(LocalDate.now());

    @Test
    void shouldCreateValidMaintenance() {
        Maintenance m = new Maintenance(
                MaintenanceID.newID(), dummyDrone, type, description, date
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

        assertThrows(IllegalArgumentException.class, () -> new Maintenance(id, null, type, description, date));
        assertThrows(IllegalArgumentException.class, () -> new Maintenance(id, dummyDrone, null, description, date));
        assertThrows(IllegalArgumentException.class, () -> new Maintenance(id, dummyDrone, type, null, date));
        assertThrows(IllegalArgumentException.class, () -> new Maintenance(id, dummyDrone, type, description, null));
    }

    @Test
    void shouldBeSameAsIfSameID() {
        MaintenanceID id = MaintenanceID.newID();

        Maintenance m1 = new Maintenance(id, dummyDrone, type, description, date);
        Maintenance m2 = new Maintenance(id, dummyDrone, type, description, date);

        assertTrue(m1.sameAs(m2));
    }

    @Test
    void shouldNotBeSameAsIfDifferentID() {
        Maintenance m1 = new Maintenance(MaintenanceID.newID(), dummyDrone, type, description, date);
        Maintenance m2 = new Maintenance(MaintenanceID.newID(), dummyDrone, type, description, date);

        assertFalse(m1.sameAs(m2));
    }
}