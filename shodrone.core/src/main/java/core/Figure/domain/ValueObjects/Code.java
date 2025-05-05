package core.Figure.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a code in the format FIG-XXXX.
 * This class is immutable and implements ValueObject interface.
 */
@Embeddable
public class Code implements Comparable<Code>, ValueObject, Serializable {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The code value in the format FIG-XXXX.
     */
    private String value;

    /**
     * Default constructor for JPA.
     */
    protected Code() {
        this.value = null;
    }

    /**
     * Constructor to create a Code object.
     * @param code the code in the format FIG-XXXX
     */
    public Code(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be null or empty");
        }

        if (!code.matches("^FIG-\\d{4}$")) {
            throw new IllegalArgumentException("Code must follow the format FIG-XXXX (e.g., FIG-0001)");
        }

        this.value = code;
    }

    /**
     * Code in the format FIG-XXXX.
     */
    @Override
    public String toString(){
        return value;
    }

    /**
     * equals method to compare two Code objects.
     * @param other the other object to compare with this object
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Code code1 = (Code) other;

        return value.equals(code1.value);
    }

    /**
     * hashCode method to generate a hash code for the Code object.
     * @return the hash code for the Code object
     */
    @Override
    public int hashCode(){
        return Objects.hash(value);
    }

    /**
     * Compares this Code object with another Code object.
     * @param o the object to be compared.
     * @return 1 if this Code is greater than the other, -1 if less, 0 if equal
     */
    @Override
    public int compareTo(Code o) {
        if (o == null) {
            return 1;
        }
        return this.value.compareTo(o.value);
    }
}
