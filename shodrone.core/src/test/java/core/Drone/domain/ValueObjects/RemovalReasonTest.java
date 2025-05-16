package core.Drone.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RemovalReasonTest {

    @Test
    void ensureRemovalReasonIsCreatedSuccessfully() {
        Map<Date, String> reasons = new HashMap<>();
        reasons.put(new Date(), "Damaged during flight");
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
        Map<Date, String> reasons1 = new HashMap<>();
        Map<Date, String> reasons2 = new HashMap<>();

        reasons1.put(now, "Outdated");
        reasons2.put(now, "Outdated");

        RemovalReason r1 = new RemovalReason(reasons1);
        RemovalReason r2 = new RemovalReason(reasons2);
        RemovalReason r3 = new RemovalReason(new HashMap<>());

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
    }

    @Test
    void ensureHashCodeIsConsistentWithEquals() {
        Date now = new Date();
        Map<Date, String> reasons1 = new HashMap<>();
        Map<Date, String> reasons2 = new HashMap<>();
        reasons1.put(now, "Malfunction");
        reasons2.put(now, "Malfunction");

        RemovalReason r1 = new RemovalReason(reasons1);
        RemovalReason r2 = new RemovalReason(reasons2);

        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void ensureToStringReturnsCorrectFormat() {
        Map<Date, String> reasons = new HashMap<>();
        Date now = new Date();
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
