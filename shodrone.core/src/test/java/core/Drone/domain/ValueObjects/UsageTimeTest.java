package core.Drone.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsageTimeTest {

    @Test
    void shouldCreateValidUsageTime() {
        UsageTime ut = new UsageTime(LocalTime.of(2, 30));
        assertEquals("02:30", ut.toString());
    }

    @Test
    void shouldThrowWhenInitializedWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new UsageTime(null));
    }

    @Test
    void shouldAddTimeCorrectly() {
        UsageTime ut = new UsageTime(LocalTime.of(1, 45));
        ut.addTime(LocalTime.of(2, 15)); // total = 4:00
        assertEquals("04:00", ut.toString());
    }

    @Test
    void shouldAddTimeWithRollover() {
        UsageTime ut = new UsageTime(LocalTime.of(23, 0));
        ut.addTime(LocalTime.of(2, 30)); // 23:00 + 2:30 = 01:30 (next day)
        assertEquals("01:30", ut.toString());
    }

    @Test
    void shouldThrowIfTimeToAddIsNull() {
        UsageTime ut = new UsageTime(LocalTime.of(5, 0));
        assertThrows(IllegalArgumentException.class, () -> ut.addTime(null));
    }

    @Test
    void shouldThrowIfTimeToAddHasSecondsOrNanos() {
        UsageTime ut = new UsageTime(LocalTime.of(5, 0));
        assertThrows(IllegalArgumentException.class, () -> ut.addTime(LocalTime.of(1, 0, 30)));
        assertThrows(IllegalArgumentException.class, () -> ut.addTime(LocalTime.of(1, 0, 0, 1_000)));
    }

    @Test
    void shouldSupportEquality() {
        UsageTime ut1 = new UsageTime(LocalTime.of(3, 15));
        UsageTime ut2 = new UsageTime(LocalTime.of(3, 15));
        assertEquals(ut1, ut2);
    }

    @Test
    void shouldInitializeFromNullAndThenAddTime() {
        UsageTime ut = new UsageTime();
        ut.addTime(LocalTime.of(2, 0));
        assertEquals("02:00", ut.toString());
    }
}