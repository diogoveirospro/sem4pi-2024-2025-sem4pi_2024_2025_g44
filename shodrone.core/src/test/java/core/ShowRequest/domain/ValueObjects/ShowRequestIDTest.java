package core.ShowRequest.domain.ValueObjects;

import core.ShowRequest.application.Service.GenerateShowRequestID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowRequestIDTest {

    @Test
    void ensureShowRequestIDValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new ShowRequestID(null));
        assertThrows(IllegalArgumentException.class, () -> new ShowRequestID(""));
        assertThrows(IllegalArgumentException.class, () -> new ShowRequestID("1234"));
    }

    @Test
    void ensureShowRequestIDEqualsWorksCorrectly() {
        ShowRequestID showRequestID1 = new ShowRequestID("REQ-1234");
        ShowRequestID showRequestID2 = new ShowRequestID("REQ-1234");
        ShowRequestID showRequestID3 = new ShowRequestID("REQ-5678");

        assertEquals(showRequestID1, showRequestID2);
        assertNotEquals(showRequestID1, showRequestID3);
    }

    @Test
    void ensureShowRequestIDToStringReturnsCorrectValue() {
        ShowRequestID showRequestID = new ShowRequestID("REQ-1234");
        String expected = "REQ-1234";
        assertEquals(expected, showRequestID.toString());
    }
}