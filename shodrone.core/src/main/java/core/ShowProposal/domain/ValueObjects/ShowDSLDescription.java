package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Represents the description of a Show DSL (Domain-Specific Language).
 * This class is immutable and serves as a value object in the domain model.
 */
@Embeddable
public class ShowDSLDescription implements ValueObject, Serializable {

    /**
     * Serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The content of the Show DSL.
     */
    private String content;

    /**
     * Default constructor for JPA.
     */
    protected ShowDSLDescription() {
    }

    /**
     * Constructor to create a ShowDSLDescription object.
     *
     * @param content the content of the Show DSL
     */
    public ShowDSLDescription(final String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Show DSL content cannot be null or blank");
        }
        this.content = content;
    }

    /**
     * Gets the content of the Show DSL.
     *
     * @return the content of the Show DSL
     */
    public String content() {
        return content;
    }

    /**
     * Checks if this ShowDSLDescription is equal to another object.
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowDSLDescription)) return false;
        ShowDSLDescription that = (ShowDSLDescription) o;
        return content.equals(that.content);
    }
}
