package core.Maintenance.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceIDTest {

    @Test
    void shouldCreateMaintenanceIDWithValue() {
        MaintenanceID id = new MaintenanceID("ID-001");
        assertEquals("ID-001", id.toString());
    }

    @Test
    void shouldGenerateUniqueID() {
        MaintenanceID id1 = MaintenanceID.newID();
        MaintenanceID id2 = MaintenanceID.newID();
        assertNotEquals(id1, id2);
    }

    @Test
    void shouldThrowWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceID(null));
    }

    @Test
    void shouldRespectEquality() {
        MaintenanceID id1 = new MaintenanceID("ABC");
        MaintenanceID id2 = new MaintenanceID("ABC");
        assertEquals(id1, id2);
    }
}