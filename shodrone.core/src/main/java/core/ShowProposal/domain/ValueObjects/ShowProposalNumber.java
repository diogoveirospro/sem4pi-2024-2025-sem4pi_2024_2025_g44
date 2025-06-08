package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Value Object representing a Show Proposal Number.
 */
@Embeddable
public class ShowProposalNumber implements Comparable<ShowProposalNumber>, Serializable, ValueObject {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The proposal number, which must not be null, empty, and must start with "PROP-".
     */
    private String proposalNumber;

    /**
     * Default constructor for JPA.
     * This constructor is required for JPA to instantiate the object.
     */
    protected ShowProposalNumber () {
        // Default constructor for JPA
    }

    /**
     * Constructor to create a ShowProposalNumber with a specific proposal number.
     * The proposal number must not be null, empty, and must start with "PROP-".
     *
     * @param proposalNumber the proposal number to set
     * @throws IllegalArgumentException if the proposal number is null, empty, or does not start with "PROP-"
     */
    public ShowProposalNumber(String proposalNumber) {
        if (proposalNumber == null || !proposalNumber.startsWith("PROP-")) {
            throw new IllegalArgumentException("Proposal number must not be null, empty, and must start with 'PROP-'");
        }
        this.proposalNumber = proposalNumber;
    }

    /**
     * Returns the proposal number.
     *
     * @return the proposal number
     */
    public String proposalNumber() {
        return proposalNumber;
    }

    /**
     * Compares this ShowProposalNumber with another ShowProposalNumber.
     *
     * @return a negative integer, zero, or a positive integer as this proposal number is less than, equal to, or greater than the specified proposal number
     */
    @Override
    public int compareTo(ShowProposalNumber o) {
        return this.proposalNumber().compareTo(o.proposalNumber());
    }
}
