package core.Figure.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeywordTest {

    @Test
    public void testValidKeywordCreation() {
        Keyword keyword = new Keyword("Example-Keyword1");
        assertEquals("Example-Keyword1", keyword.toString());
    }

    @Test
    public void testKeywordTooShortThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Keyword("A"));
    }

    @Test
    public void testKeywordTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Keyword("a".repeat(31)));
    }

    @Test
    public void testKeywordWithInvalidCharactersThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Keyword("invalid*keyword"));
        assertThrows(IllegalArgumentException.class, () -> new Keyword("invalid keyword"));
    }

    @Test
    public void testNullKeywordThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Keyword(null));
    }

    @Test
    public void testBlankKeywordThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Keyword("   "));
    }

    @Test
    public void testEqualsIgnoresCaseAndAccents() {
        Keyword k1 = new Keyword("áéíóú");
        Keyword k2 = new Keyword("AEIOU");
        assertEquals(k1, k2);
    }

    @Test
    public void testHashCodeConsistency() {
        Keyword k1 = new Keyword("Word");
        Keyword k2 = new Keyword("word");
        assertEquals(k1.hashCode(), k2.hashCode());
    }

    @Test
    public void testCompareToIgnoresCaseAndAccents() {
        Keyword k1 = new Keyword("Água");
        Keyword k2 = new Keyword("agua");
        assertEquals(0, k1.compareTo(k2));
    }

    @Test
    public void testCompareToOrdersCorrectly() {
        Keyword k1 = new Keyword("apple");
        Keyword k2 = new Keyword("banana");
        assertTrue(k1.compareTo(k2) < 0);
    }
}
