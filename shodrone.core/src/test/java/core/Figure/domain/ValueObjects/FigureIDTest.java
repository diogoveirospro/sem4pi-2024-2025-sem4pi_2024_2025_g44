package core.Figure.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FigureIDTest {

    @Test
    public void testValidFigureIDCreation() {
        Code code = new Code("FIG-0001");
        Version version = new Version("1.0.0");

        FigureID figureID = new FigureID(code, version);

        assertEquals(code, figureID.code());
        assertEquals(version, figureID.version());
    }

    @Test
    public void testNullCodeThrowsException() {
        Version version = new Version("1.0.0");

        assertThrows(IllegalArgumentException.class, () -> new FigureID(null, version));
    }

    @Test
    public void testNullVersionThrowsException() {
        Code code = new Code("FIG-0001");

        assertThrows(IllegalArgumentException.class, () -> new FigureID(code, null));
    }

    @Test
    public void testEqualsAndHashCode() {
        Code code1 = new Code("FIG-0001");
        Version version1 = new Version("1.0.0");

        Code code2 = new Code("FIG-0001");
        Version version2 = new Version("1.0.0");

        FigureID id1 = new FigureID(code1, version1);
        FigureID id2 = new FigureID(code2, version2);

        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    public void testToString() {
        Code code = new Code("FIG-0001");
        Version version = new Version("1.0.0");

        FigureID id = new FigureID(code, version);
        String expected = "FigureID{code=FIG-0001, version=1.0.0}";

        assertEquals(expected, id.toString());
    }

    @Test
    public void testCompareToEqual() {
        FigureID id1 = new FigureID(new Code("FIG-0001"), new Version("1.0.0"));
        FigureID id2 = new FigureID(new Code("FIG-0001"), new Version("1.0.0"));

        assertEquals(0, id1.compareTo(id2));
    }

    @Test
    public void testCompareToDifferentCode() {
        FigureID id1 = new FigureID(new Code("FIG-0001"), new Version("1.0.0"));
        FigureID id2 = new FigureID(new Code("FIG-0002"), new Version("1.0.0"));

        assertTrue(id1.compareTo(id2) < 0);
        assertTrue(id2.compareTo(id1) > 0);
    }

    @Test
    public void testCompareToDifferentVersion() {
        FigureID id1 = new FigureID(new Code("FIG-0001"), new Version("1.0.0"));
        FigureID id2 = new FigureID(new Code("FIG-0001"), new Version("1.1.0"));

        assertTrue(id1.compareTo(id2) < 0);
        assertTrue(id2.compareTo(id1) > 0);
    }
}
