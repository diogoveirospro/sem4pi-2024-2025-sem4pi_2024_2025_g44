package core.ShowProposal.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowDSLDescriptionTest {

    @Test
    void testValidContent() {
        ShowDSLDescription description = new ShowDSLDescription("Valid DSL Content");
        assertEquals("Valid DSL Content", description.content());
    }

    @Test
    void testNullContentThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ShowDSLDescription(null);
        });
        assertEquals("Show DSL content cannot be null or blank", exception.getMessage());
    }

    @Test
    void testBlankContentThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ShowDSLDescription("   ");
        });
        assertEquals("Show DSL content cannot be null or blank", exception.getMessage());
    }

    @Test
    void testEquality() {
        ShowDSLDescription description1 = new ShowDSLDescription("DSL Content");
        ShowDSLDescription description2 = new ShowDSLDescription("DSL Content");
        assertEquals(description1, description2);
    }

    @Test
    void testInequality() {
        ShowDSLDescription description1 = new ShowDSLDescription("DSL Content 1");
        ShowDSLDescription description2 = new ShowDSLDescription("DSL Content 2");
        assertNotEquals(description1, description2);
    }
}