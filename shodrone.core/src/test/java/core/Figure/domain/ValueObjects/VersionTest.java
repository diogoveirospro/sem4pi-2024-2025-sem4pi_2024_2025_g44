package core.Figure.domain.ValueObjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VersionTest {

    @Test
    void validVersionShouldBeCreated() {
        Version version = new Version("1.2.3");
        assertEquals("1.2.3", version.toString());
    }

    @Test
    void nullVersionShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Version(null));
    }

    @Test
    void emptyVersionShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Version(""));
    }

    @Test
    void invalidFormatVersionShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Version("1.2"));
        assertThrows(IllegalArgumentException.class, () -> new Version("1.2.3.4"));
        assertThrows(IllegalArgumentException.class, () -> new Version("1.a.3"));
        assertThrows(IllegalArgumentException.class, () -> new Version("abc"));
    }

    @Test
    void equalVersionsShouldBeEqual() {
        Version v1 = new Version("1.0.0");
        Version v2 = new Version("1.0.0");
        assertEquals(v1, v2);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    void differentVersionsShouldNotBeEqual() {
        Version v1 = new Version("1.0.0");
        Version v2 = new Version("1.0.1");
        assertNotEquals(v1, v2);
    }

    @Test
    void compareToShouldWorkCorrectly() {
        Version v1 = new Version("1.0.0");
        Version v2 = new Version("1.0.1");
        Version v3 = new Version("2.0.0");

        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v3) < 0);
        assertTrue(v3.compareTo(v1) > 0);
        assertEquals(0, v1.compareTo(new Version("1.0.0")));
    }

    @Test
    void compareToNullShouldReturnPositive() {
        Version version = new Version("1.2.3");
        assertTrue(version.compareTo(null) > 0);
    }
}
