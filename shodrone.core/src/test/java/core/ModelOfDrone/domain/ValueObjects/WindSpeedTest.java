package core.ModelOfDrone.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindSpeedTest {

    @Test
    void testValidWindSpeedCreation() {
        WindSpeed windSpeed = new WindSpeed(0, 10);
        assertNotNull(windSpeed);
        assertEquals(0, windSpeed.minSpeed());
        assertEquals(10, windSpeed.maxSpeed());
    }

    @Test
    void testInvalidWindSpeedNegativeMin() {
        assertThrows(IllegalArgumentException.class, () -> new WindSpeed(-1, 10));
    }

    @Test
    void testInvalidWindSpeedMaxNotGreaterThanMin() {
        assertThrows(IllegalArgumentException.class, () -> new WindSpeed(10, 10));
        assertThrows(IllegalArgumentException.class, () -> new WindSpeed(10, 5));
    }

    @Test
    void testEqualsAndHashCode() {
        WindSpeed ws1 = new WindSpeed(0, 10);
        WindSpeed ws2 = new WindSpeed(0, 10);
        WindSpeed ws3 = new WindSpeed(5, 15);

        assertEquals(ws1, ws2);
        assertEquals(ws1.hashCode(), ws2.hashCode());
        assertNotEquals(ws1, ws3);
    }

    @Test
    void testEqualsWithDifferentObject() {
        WindSpeed ws = new WindSpeed(0, 10);
        assertNotEquals(ws, "0-10");
    }

    @Test
    void testEqualsWithNull() {
        WindSpeed ws = new WindSpeed(0, 10);
        assertNotEquals(ws, null);
    }

    @Test
    void testToString() {
        WindSpeed windSpeed = new WindSpeed(0, 20);
        assertEquals("0 < wind <= 20", windSpeed.toString());
    }

    @Test
    void testProtectedConstructorForJPA() {
        WindSpeed windSpeed = new WindSpeed(); // only for coverage
        assertNotNull(windSpeed);
    }
}
