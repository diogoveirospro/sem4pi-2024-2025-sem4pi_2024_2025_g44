package core.Maintenance.domain.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaintenanceTypeTest {

    @Test
    void shouldCreateValidType() {
        MaintenanceType type = new MaintenanceType("Helix Verification");
        assertEquals("Helix Verification", type.toString());
    }

    @Test
    void shouldThrowWhenNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(null));
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(""));
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType("   "));
    }

    @Test
    void shouldBeEqualIfSameName() {
        MaintenanceType t1 = new MaintenanceType("Battery");
        MaintenanceType t2 = new MaintenanceType("Battery");
        assertEquals(t1, t2);
        assertEquals(t1.identity(), t2.identity());
    }

    @Test
    void shouldHaveCorrectHashCode() {
        MaintenanceType t1 = new MaintenanceType("Firmware");
        MaintenanceType t2 = new MaintenanceType("Firmware");
        assertEquals(t1.hashCode(), t2.hashCode());
    }
}