package core.Figure.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DSLDescriptionTest {

    @Test
    void validDSLDescriptionShouldBeCreatedSuccessfully() {
        List<String> codeLines = Arrays.asList("line1", "line2", "line3");
        DSLDescription description = new DSLDescription(codeLines, "1.0.0");
        assertEquals(codeLines, description.codeLines());
        assertEquals("1.0.0", description.version());
    }

    @Test
    void nullDSLCodeLinesShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DSLDescription(null, "1.0.0");
        });
        assertEquals("DSL code lines cannot be null or empty", exception.getMessage());
    }

    @Test
    void emptyDSLCodeLinesShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DSLDescription(Arrays.asList(), "1.0.0");
        });
        assertEquals("DSL code lines cannot be null or empty", exception.getMessage());
    }

    @Test
    void nullDSLVersionShouldThrowException() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DSLDescription(codeLines, null);
        });
        assertEquals("DSL version cannot be null or blank", exception.getMessage());
    }

    @Test
    void blankDSLVersionShouldThrowException() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DSLDescription(codeLines, "");
        });
        assertEquals("DSL version cannot be null or blank", exception.getMessage());
    }

    @Test
    void invalidDSLVersionFormatShouldThrowException() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DSLDescription(codeLines, "1.0");
        });
        assertEquals("Invalid DSL version format. Expected format: X.Y.Z", exception.getMessage());
    }

    @Test
    void validDSLVersionShouldPass() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        DSLDescription description = new DSLDescription(codeLines, "1.0.0");
        assertNotNull(description);
    }

    @Test
    void dslDescriptionToStringShouldBeFormattedCorrectly() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        DSLDescription description = new DSLDescription(codeLines, "1.0.0");
        String expected = "line1\nline2\nVersion: 1.0.0";
        assertEquals(expected, description.toString());
    }

    @Test
    void equalsShouldReturnTrueForSameObjects() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        DSLDescription description1 = new DSLDescription(codeLines, "1.0.0");
        DSLDescription description2 = new DSLDescription(codeLines, "1.0.0");
        assertEquals(description1, description2);
        assertEquals(description1.hashCode(), description2.hashCode());
    }

    @Test
    void equalsShouldReturnFalseForDifferentObjects() {
        List<String> codeLines1 = Arrays.asList("line1", "line2");
        List<String> codeLines2 = Arrays.asList("line3", "line4");
        DSLDescription description1 = new DSLDescription(codeLines1, "1.0.0");
        DSLDescription description2 = new DSLDescription(codeLines2, "1.0.0");
        assertNotEquals(description1, description2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentVersion() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        DSLDescription description1 = new DSLDescription(codeLines, "1.0.0");
        DSLDescription description2 = new DSLDescription(codeLines, "1.1.0");
        assertNotEquals(description1, description2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        List<String> codeLines = Arrays.asList("line1", "line2");
        DSLDescription description = new DSLDescription(codeLines, "1.0.0");
        assertNotEquals(description, "Some other object");
    }
}
