package core.Drone.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RemovalReasonTest {

    @Test
    void ensureRemovalReasonIsCreatedSuccessfully() {
        Map<LocalDate, String> reasons = new HashMap<>();
        reasons.put(LocalDate.now(), "Damaged during flight");
        RemovalReason removalReason = new RemovalReason(reasons);

        assertNotNull(removalReason);
        assertEquals(1, removalReason.value().size());
    }

    @Test
    void ensureAddReasonAddsEntryWithCurrentDate() {
        RemovalReason removalReason = new RemovalReason(new HashMap<>());
        removalReason.addReason("Battery failure");

        assertEquals(1, removalReason.value().size());

        // Check if the value added matches
        String reasonText = removalReason.value().values().iterator().next();
        assertEquals("Battery failure", reasonText);
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        Date now = new Date();
        Map<LocalDate, String> reasons1 = new HashMap<>();
        Map<LocalDate, String> reasons2 = new HashMap<>();

        reasons1.put(LocalDate.now(), "Outdated");
        reasons2.put(LocalDate.now(), "Outdated");

        RemovalReason r1 = new RemovalReason(reasons1);
        RemovalReason r2 = new RemovalReason(reasons2);
        RemovalReason r3 = new RemovalReason(new HashMap<>());

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
    }

    @Test
    void ensureHashCodeIsConsistentWithEquals() {
        LocalDate now = LocalDate.now();
        Map<LocalDate, String> reasons1 = new HashMap<>();
        Map<LocalDate, String> reasons2 = new HashMap<>();
        reasons1.put(now, "Malfunction");
        reasons2.put(now, "Malfunction");

        RemovalReason r1 = new RemovalReason(reasons1);
        RemovalReason r2 = new RemovalReason(reasons2);

        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void ensureToStringReturnsCorrectFormat() {
        Map<LocalDate, String> reasons = new HashMap<>();
        LocalDate now = LocalDate.now();
        reasons.put(now, "Damaged");

        RemovalReason r = new RemovalReason(reasons);
        String expected = reasons.toString();

        assertEquals(expected, r.toString());
    }

    @Test
    void ensureEqualsFailsWithDifferentObjectOrNull() {
        RemovalReason r = new RemovalReason(new HashMap<>());

        assertNotEquals(r, null);
        assertNotEquals(r, "Just a string");
    }
}
