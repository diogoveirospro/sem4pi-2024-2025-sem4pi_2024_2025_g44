package core.ShowProposal.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowProposalDocumentTest {

    @Test
    void testConstructorWithValidContent() {
        ShowProposalDocument doc = new ShowProposalDocument("content", "/tmp/doc.txt");
        assertEquals("content", doc.toString());
    }

    @Test
    void testConstructorThrowsOnNullContent() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalDocument(null, "/tmp/doc.txt"));
    }

    @Test
    void testConstructorThrowsOnEmptyContent() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalDocument("", "/tmp/doc.txt"));
    }

    @Test
    void testAddFilePathSetsFilePath() {
        ShowProposalDocument doc = new ShowProposalDocument("content", null);
        doc.addFilePath("/tmp/doc.txt");
        assertEquals("content", doc.toString());
    }

    @Test
    void testAddFilePathThrowsOnNull() {
        ShowProposalDocument doc = new ShowProposalDocument("content", null);
        assertThrows(IllegalArgumentException.class, () -> doc.addFilePath(null));
    }

    @Test
    void testAddFilePathThrowsOnEmpty() {
        ShowProposalDocument doc = new ShowProposalDocument("content", null);
        assertThrows(IllegalArgumentException.class, () -> doc.addFilePath(""));
    }

    @Test
    void testToStringReturnsContent() {
        ShowProposalDocument doc = new ShowProposalDocument("abc123", "/tmp/doc.txt");
        assertEquals("abc123", doc.toString());
    }
}