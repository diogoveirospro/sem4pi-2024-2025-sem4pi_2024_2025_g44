package core.Drone.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerialNumberTest {

    @Test
    void ensurePositionIsCreatedSuccessfully() {
        SerialNumber serialNumber = new SerialNumber(1233);
        assertNotNull(serialNumber);
        assertEquals("1233", serialNumber.toString());
    }

    @Test
    void ensureSerialNumberValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new SerialNumber(-1));
        assertThrows(IllegalArgumentException.class, () -> new SerialNumber(0));
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        SerialNumber serialNumber1 = new SerialNumber(122);
        SerialNumber serialNumber2 = new SerialNumber(122);
        SerialNumber serialNumber3 = new SerialNumber(123);

        assertEquals(serialNumber1, serialNumber2);
        assertNotEquals(serialNumber1, serialNumber3);
    }

    @Test
    void ensureHashCodeIsConsistentWithEquals() {
        SerialNumber serialNumber1 = new SerialNumber(100);
        SerialNumber serialNumber2 = new SerialNumber(100);

        assertEquals(serialNumber1.hashCode(), serialNumber2.hashCode());
    }

    @Test
    void ensureValueReturnsCorrectInteger() {
        SerialNumber serialNumber = new SerialNumber(5678);
        assertEquals(5678, serialNumber.value());
    }

    @Test
    void ensureEqualsWithDifferentObjectTypeAndNull() {
        SerialNumber serialNumber = new SerialNumber(1111);

        assertNotEquals(serialNumber, null);
        assertNotEquals(serialNumber, "Some String");
    }


}
