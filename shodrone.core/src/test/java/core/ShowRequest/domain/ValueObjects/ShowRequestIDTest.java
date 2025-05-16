package core.ShowRequest.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowRequestIDTest {
    @Test
    void ensureShowRequestIDIsCreatedSuccessfully() {
        ShowRequestID showRequestID = new ShowRequestID("customer", "crmcollaborator", "12-12-2000", "12:12");
        assertNotNull(showRequestID);
        String toString = "customer email = " + "customer" + " | collaborator email = " + "crmcollaborator" + " | date = " + "12-12-2000" + " | time = " + "12:12";
        assertEquals(toString, showRequestID.toString());
    }

    @Test
    void ensureShowRequestIDValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new ShowRequestID(null, null, null, null));
    }

    @Test
    void ensureShowRequestIDEqualsWorksCorrectly() {
        ShowRequestID showRequestID1 = new ShowRequestID("customer1", "crmcollaborator", "12-12-2000", "12:12");
        ShowRequestID showRequestID2 = new ShowRequestID("customer1", "crmcollaborator", "12-12-2000", "12:12");
        ShowRequestID showRequestID3 = new ShowRequestID("customer2", "crmcollaborator", "12-12-2000", "12:12");

        assertEquals(showRequestID1, showRequestID2);
        assertNotEquals(showRequestID1, showRequestID3);
    }

    @Test
    void ensureShowRequestIDToStringReturnsCorrectValue() {
        ShowRequestID showRequestID = new ShowRequestID("customer", "crmcollaborator", "12-12-2000", "12:12");
        String toString = "customer email = " + "customer" + " | collaborator email = " + "crmcollaborator" + " | date = " + "12-12-2000" + " | time = " + "12:12";
        assertEquals(toString, showRequestID.toString());
    }

}