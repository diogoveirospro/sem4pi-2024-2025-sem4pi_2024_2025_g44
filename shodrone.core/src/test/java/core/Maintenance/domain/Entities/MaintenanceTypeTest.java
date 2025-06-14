package core.Maintenance.domain.Entities;

import core.Shared.domain.ValueObjects.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaintenanceTypeTest {

    @Test
    void shouldCreateValidType() {
        MaintenanceType type = new MaintenanceType(new Name("Helix Verification"),true);
        assertEquals("Helix Verification", type.toString());
    }

    @Test
    void shouldThrowWhenNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(null,true));
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(new Name(""),true));
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(new Name("   "),true));
    }

    @Test
    void shouldBeEqualIfSameName() {
        MaintenanceType t1 = new MaintenanceType(new Name("Battery"),true);
        MaintenanceType t2 = new MaintenanceType(new Name("Battery"),true);
        assertEquals(t1, t2);
        assertEquals(t1.identity(), t2.identity());
    }

    @Test
    void shouldHaveCorrectHashCode() {
        MaintenanceType t1 = new MaintenanceType(new Name("Firmware"),true);
        MaintenanceType t2 = new MaintenanceType(new Name("Firmware"),true);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void shouldRenameSuccessfully() {
        MaintenanceType type = new MaintenanceType(new Name("Inicial"),true);
        type.rename(new Name("Firmware Update"));

        assertEquals("Firmware Update", type.toString());
    }

    @Test
    void shouldThrowWhenRenameToNullOrBlank() {
        MaintenanceType type = new MaintenanceType(new Name("Inicial"),true);

        assertThrows(IllegalArgumentException.class, () -> type.rename(null));
        assertThrows(IllegalArgumentException.class, () -> type.rename(new Name("")));
        assertThrows(IllegalArgumentException.class, () -> type.rename(new  Name("   ")));
    }
}