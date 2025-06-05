package core.ProposalDeliveryInfo.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProposalDeliveryInfoCodeTest {

    @Test
    void ensureValidCodeIsCreated() {
        ProposalDeliveryInfoCode code = new ProposalDeliveryInfoCode("VALID_CODE");
        assertNotNull(code);
        assertEquals("VALID_CODE", code.code());
    }

    @Test
    void ensureCodeCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new ProposalDeliveryInfoCode(null));
    }

    @Test
    void ensureCodeCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new ProposalDeliveryInfoCode(""));
    }

    @Test
    void ensureEqualsWorksForSameCode() {
        ProposalDeliveryInfoCode code1 = new ProposalDeliveryInfoCode("SAME_CODE");
        ProposalDeliveryInfoCode code2 = new ProposalDeliveryInfoCode("SAME_CODE");
        assertEquals(code1, code2);
    }

    @Test
    void ensureEqualsWorksForDifferentCodes() {
        ProposalDeliveryInfoCode code1 = new ProposalDeliveryInfoCode("CODE1");
        ProposalDeliveryInfoCode code2 = new ProposalDeliveryInfoCode("CODE2");
        assertNotEquals(code1, code2);
    }

    @Test
    void ensureCompareToWorksCorrectly() {
        ProposalDeliveryInfoCode code1 = new ProposalDeliveryInfoCode("A_CODE");
        ProposalDeliveryInfoCode code2 = new ProposalDeliveryInfoCode("B_CODE");
        assertTrue(code1.compareTo(code2) < 0);
        assertTrue(code2.compareTo(code1) > 0);
    }
}