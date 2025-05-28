package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ShowProposalNumber implements Comparable<ShowProposalNumber>, Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private String proposalNumber;

    protected ShowProposalNumber () {
        // Default constructor for JPA
    }

    public ShowProposalNumber(String proposalNumber) {
        if (proposalNumber == null || !proposalNumber.startsWith("PROP-")) {
            throw new IllegalArgumentException("Proposal number must not be null, empty, and must start with 'PROP-'");
        }
        this.proposalNumber = proposalNumber;
    }

    public String proposalNumber() {
        return proposalNumber;
    }

    @Override
    public int compareTo(ShowProposalNumber o) {
        return this.proposalNumber().compareTo(o.proposalNumber());
    }
}
