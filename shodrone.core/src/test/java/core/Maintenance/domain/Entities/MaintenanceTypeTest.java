package core.Maintenance.domain.Entities;

import core.Shared.domain.ValueObjects.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaintenanceTypeTest {

    @Test
    void shouldCreateValidType() {
        MaintenanceType type = new MaintenanceType(new Name("Helix Verification"));
        assertEquals("Helix Verification", type.toString());
    }

    @Test
    void shouldThrowWhenNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(null));
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(new Name("")));
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(new Name("   ")));
    }

    @Test
    void shouldBeEqualIfSameName() {
        MaintenanceType t1 = new MaintenanceType(new Name("Battery"));
        MaintenanceType t2 = new MaintenanceType(new Name("Battery"));
        assertEquals(t1, t2);
        assertEquals(t1.identity(), t2.identity());
    }

    @Test
    void shouldHaveCorrectHashCode() {
        MaintenanceType t1 = new MaintenanceType(new Name("Firmware"));
        MaintenanceType t2 = new MaintenanceType(new Name("Firmware"));
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void shouldRenameSuccessfully() {
        MaintenanceType type = new MaintenanceType(new Name("Inicial"));
        type.rename(new Name("Firmware Update"));

        assertEquals("Firmware Update", type.toString());
    }

    @Test
    void shouldThrowWhenRenameToNullOrBlank() {
        MaintenanceType type = new MaintenanceType(new Name("Inicial"));

        assertThrows(IllegalArgumentException.class, () -> type.rename(null));
        assertThrows(IllegalArgumentException.class, () -> type.rename(new Name("")));
        assertThrows(IllegalArgumentException.class, () -> type.rename(new  Name("   ")));
    }
}