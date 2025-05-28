package core.ShowProposal.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowProposalNumberTest {

    @Test
    void ensureShowProposalNumberIsCreatedWithValidArgument() {
        ShowProposalNumber proposalNumber = new ShowProposalNumber("PROP-12345");
        assertEquals("PROP-12345", proposalNumber.proposalNumber());
    }

    @Test
    void ensureExceptionIsThrownForNullProposalNumber() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalNumber(null));
    }

    @Test
    void ensureExceptionIsThrownForEmptyProposalNumber() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalNumber(""));
    }

    @Test
    void ensureExceptionIsThrownForInvalidPrefix() {
        assertThrows(IllegalArgumentException.class, () -> new ShowProposalNumber("12345"));
    }

    @Test
    void ensureProposalNumbersAreComparable() {
        ShowProposalNumber proposalNumber1 = new ShowProposalNumber("PROP-12345");
        ShowProposalNumber proposalNumber2 = new ShowProposalNumber("PROP-12346");
        assertTrue(proposalNumber1.compareTo(proposalNumber2) < 0);
    }

    @Test
    void ensureProposalNumbersWithSameValueAreEqual() {
        ShowProposalNumber proposalNumber1 = new ShowProposalNumber("PROP-12345");
        ShowProposalNumber proposalNumber2 = new ShowProposalNumber("PROP-12345");
        assertEquals(0, proposalNumber1.compareTo(proposalNumber2));
    }
}