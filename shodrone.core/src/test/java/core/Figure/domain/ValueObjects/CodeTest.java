package core.Figure.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    @Test
    void validCodeShouldBeCreatedSuccessfully() {
        Code code = new Code("FIG-1234");
        assertEquals("FIG-1234", code.toString());
    }

    @Test
    void nullCodeShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Code(null);
        });
        assertEquals("Code cannot be null or empty", exception.getMessage());
    }

    @Test
    void emptyCodeShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Code("");
        });
        assertEquals("Code cannot be null or empty", exception.getMessage());
    }

    @Test
    void codeWithInvalidFormatShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Code("FIG1234"));
        assertThrows(IllegalArgumentException.class, () -> new Code("FIG-12A4"));
        assertThrows(IllegalArgumentException.class, () -> new Code("FIG-123"));
        assertThrows(IllegalArgumentException.class, () -> new Code("fig-0001"));
    }

    @Test
    void codesWithSameValueShouldBeEqual() {
        Code code1 = new Code("FIG-0001");
        Code code2 = new Code("FIG-0001");
        assertEquals(code1, code2);
        assertEquals(code1.hashCode(), code2.hashCode());
    }

    @Test
    void codesWithDifferentValuesShouldNotBeEqual() {
        Code code1 = new Code("FIG-0001");
        Code code2 = new Code("FIG-0002");
        assertNotEquals(code1, code2);
    }

    @Test
    void codeShouldBeComparable() {
        Code code1 = new Code("FIG-0001");
        Code code2 = new Code("FIG-0002");
        assertTrue(code1.compareTo(code2) < 0);
        assertTrue(code2.compareTo(code1) > 0);
        assertEquals(0, code1.compareTo(new Code("FIG-0001")));
    }

    @Test
    void compareToNullShouldReturnPositive() {
        Code code = new Code("FIG-0001");
        assertEquals(1, code.compareTo(null));
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        Code code = new Code("FIG-0001");
        assertNotEquals(code, "FIG-0001");
    }
}

