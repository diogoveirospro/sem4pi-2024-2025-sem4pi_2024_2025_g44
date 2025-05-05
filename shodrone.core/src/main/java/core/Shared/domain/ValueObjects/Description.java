package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.functional.Either;
import eapli.framework.strings.StringMixin;
import eapli.framework.validations.CheckIf;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Represents the description of a figure.
 * This class is immutable and implements ValueObject and StringMixin interfaces.
 */
@Embeddable
public class Description implements ValueObject, Serializable, StringMixin {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The description of the figure.
     */
    private final String value;

    /**
     * The description of the figure.
     *
     * @param value the description of the figure
     * @throws IllegalArgumentException if the value is null or empty
     */
    public Description(final String value) {
        this.value = value;
    }

    /**
     * Default constructor for JPA.
     */
    protected Description() {
        this.value = null;
    }

    /**
     * Creates a Description if the input is not null or empty.
     * Throws IllegalArgumentException otherwise.
     *
     * @param value the description of the figure
     * @return a Description object
     * @throws IllegalArgumentException if the value is null or empty
     */
    public static Description valueOf(final String value) {
        return tryValueOf(value).rightValueOrElseThrow(IllegalArgumentException::new);
    }

    /**
     * Creates a Description if the input is not null or empty.
     * Returns an error message otherwise.
     *
     * @param value the description of the figure
     * @return an Either containing the Description or an error message
     */
    public static Either<String, Description> tryValueOf(final String value) {
        return CheckIf.nonEmpty(value, () -> new Description(value), () -> "Description should neither be null nor empty");
    }

    /**
     * Returns the length of the description.
     * @return the length of the description
     */
    public int length() {
        return this.value.length();
    }

    /**
     * Returns the description as a string.
     * @return the description as a string
     */
    public String toString() {
        return this.value;
    }

    /**
     * equals method to compare two Description objects.
     * @param o the object to compare with this object
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Description)) {
            return false;
        } else {
            Description other = (Description)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$value = this.value;
                Object other$value = other.value;
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                return true;
            }
        }
    }

    /**
     * Checks if the other object can be compared with this object.
     * @param other the object to compare with this object
     * @return true if the other object can be compared with this object, false otherwise
     */
    protected boolean canEqual(final Object other) {
        return other instanceof Description;
    }

    /**
     * hashCode method to generate a hash code for the Description object.
     * @return the hash code for the Description object
     */
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $value = this.value;
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }
}
