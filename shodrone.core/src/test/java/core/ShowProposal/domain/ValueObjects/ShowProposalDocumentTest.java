package core.ShowProposal.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowProposalDocumentTest {

    @Test
    void testConstructorWithValidContent() {
        ShowProposalDocument doc = new ShowProposalDocument("content",
                "content".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertEquals("content", doc.toString());
    }

    @Test
    void testConstructorThrowsOnNullContent() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalDocument(null, null));
    }

    @Test
    void testConstructorThrowsOnEmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalDocument("",
                "".getBytes(java.nio.charset.StandardCharsets.UTF_8)));
    }

    @Test
    void testToStringReturnsContent() {
        ShowProposalDocument doc = new ShowProposalDocument("abc123",
                "abc123".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertEquals("abc123", doc.toString());
    }
}