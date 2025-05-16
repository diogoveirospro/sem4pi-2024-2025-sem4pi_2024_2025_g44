package core.ModelOfDrone.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionToleranceTest {

    @Test
    void testValidPositionToleranceCreation() {
        PositionTolerance pt = new PositionTolerance(0.75);
        assertEquals(0.75, pt.value());
    }

    @Test
    void testZeroTolerance() {
        PositionTolerance pt = new PositionTolerance(0.0);
        assertEquals(0.0, pt.value());
    }

    @Test
    void testNegativeTolerance() {
        PositionTolerance pt = new PositionTolerance(-1.0);
        assertEquals(-1.0, pt.value());
    }

    @Test
    void testEqualsAndHashCode() {
        PositionTolerance pt1 = new PositionTolerance(1.0);
        PositionTolerance pt2 = new PositionTolerance(1.0);
        PositionTolerance pt3 = new PositionTolerance(2.0);

        assertEquals(pt1, pt2);
        assertEquals(pt1.hashCode(), pt2.hashCode());
        assertNotEquals(pt1, pt3);
    }

    @Test
    void testEqualsWithDifferentObjectType() {
        PositionTolerance pt = new PositionTolerance(1.0);
        assertNotEquals(pt, "1.0");
    }

    @Test
    void testEqualsWithNull() {
        PositionTolerance pt = new PositionTolerance(1.0);
        assertNotEquals(pt, null);
    }

    @Test
    void testToString() {
        PositionTolerance pt = new PositionTolerance(1.23);
        assertEquals("1.23", pt.toString());
    }

    @Test
    void testDefaultConstructorForJPA() {
        PositionTolerance pt = new PositionTolerance(); // Para cobertura
        assertNotNull(pt);
    }
}
