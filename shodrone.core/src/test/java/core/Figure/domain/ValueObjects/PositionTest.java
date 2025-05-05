package core.Figure.domain.ValueObjects;

import core.Customer.domain.ValueObjects.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void ensurePositionIsCreatedSuccessfully() {
        Position position = new Position("Manager");
        assertNotNull(position);
        assertEquals("Manager", position.toString());
    }

    @Test
    void ensurePositionValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Position(""));
        assertThrows(IllegalArgumentException.class, () -> new Position(null));
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        Position position1 = new Position("Manager");
        Position position2 = new Position("Manager");
        Position position3 = new Position("Developer");

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
    }

    @Test
    void ensureToStringReturnsCorrectValue() {
        Position position = new Position("Developer");
        assertEquals("Developer", position.toString());
    }
}