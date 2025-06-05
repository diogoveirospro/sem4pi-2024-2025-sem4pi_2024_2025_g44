package core.ProposalDeliveryInfo.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Represents a code for proposal delivery information.
 * This is a code that is sent to the user when a proposal is delivered.
 * This class implements Comparable and ValueObject interfaces.
 */
@Embeddable
public class ProposalDeliveryInfoCode implements Comparable<ProposalDeliveryInfoCode>, ValueObject, Serializable {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The code for the proposal delivery information.
     */
    private String code;

    /**
     * Constructor for ProposalDeliveryInfoCode.
     * @param code the code for the proposal delivery information
     * @throws IllegalArgumentException if the code is null or empty
     */
    public ProposalDeliveryInfoCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Proposal delivery code cannot be null or empty");
        }
        this.code = code;
    }

    /**
     * Default constructor for JPA.
     */
    public ProposalDeliveryInfoCode() {}

    /**
     * Returns the code of the proposal delivery information.
     *
     * @return the code of the proposal delivery information
     */
    public String code() {
        return code;
    }

    /**
     * Checks if this ProposalDeliveryInfoCode is equal to another object.
     * @param o the object to compare with this ProposalDeliveryInfoCode
     * @return true if the object is a ProposalDeliveryInfoCode with the same code, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProposalDeliveryInfoCode)) return false;
        ProposalDeliveryInfoCode that = (ProposalDeliveryInfoCode) o;
        return code.equals(that.code);
    }

    /**
     * Returns the hash code of this ProposalDeliveryInfoCode.
     *
     * @return the hash code of this ProposalDeliveryInfoCode
     */
    @Override
    public int compareTo(ProposalDeliveryInfoCode o) {
        return code.compareTo(o.code());
    }
}
