package core.ShowProposal.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    @Test
    void ensureVideoIsCreatedWithValidArguments() {
        Video video = new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        assertEquals("Test Video", video.title());
        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ", video.url());
    }

    @Test
    void ensureVideoTitleIsAccessible() {
        Video video = new Video("Sample Title", "https://example.com");
        assertEquals("Sample Title", video.title());
    }

    @Test
    void ensureVideoUrlIsAccessible() {
        Video video = new Video("Sample Title", "https://example.com");
        assertEquals("https://example.com", video.url());
    }

    @Test
    void ensureToStringReturnsCorrectFormat() {
        Video video = new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        assertEquals("Title: Test Video URL:https://www.youtube.com/watch?v=dQw4w9WgXcQ", video.toString());
    }
}