package core.Figure.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a unique identifier for a Figure in the system.
 * This class is immutable and implements ValueObject interface.
 */
@Embeddable
public class FigureID implements ValueObject, Comparable<FigureID>, Serializable {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The code of the Figure.
     */
    @Embedded
    private Code code;

    /**
     * The version of the Figure.
     */
    @Embedded
    private Version version;

    /**
     * Constructor of the FigureID.
     */
    public FigureID(Code code, Version version) {
        if (code == null || version == null) {
            throw new IllegalArgumentException("Code and Version cannot be null");
        }
        this.code = code;
        this.version = version;
    }

    /**
     * Default constructor for JPA.
     */
    protected FigureID() {
    }

    /**
     * Returns the code of the Figure.
     *
     * @return the code of the Figure
     */
    public Code code() {
        return code;
    }

    /**
     * Returns the version of the Figure.
     *
     * @return the version of the Figure
     */
    public Version version() {
        return version;
    }

    /**
     * Returns the string representation of this FigureID object.
     *
     * @return the string representation of this FigureID object
     */
    @Override
    public String toString(){
        return "FigureID{" +
                "code=" + code +
                ", version=" + version +
                '}';
    }

    /**
     * Checks if this FigureID object is equal to another object.
     *
     * @param o the object to be compared
     * @return true if this FigureID object is equal to the specified object; false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FigureID other = (FigureID) o;
        return code.equals(other.code()) && version.equals(other.version());
    }

    /**
     * Hash code method to generate a hash code for the FigureID object.
     *
     * @return the hash code for the FigureID object
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, version);
    }

    /**
     * Compares this FigureID object with another FigureID object.
     *
     * @param o the object to be compared
     * @return 0 if they are equal, a negative number if this object is less than the other, and a positive number if
     * this object is greater than the other
     */
    @Override
    public int compareTo(FigureID o) {
        if (this == o) return 0;
        if (o == null) return 1;

        int codeComparison = this.code.compareTo(o.code);
        if (codeComparison != 0) {
            return codeComparison;
        }

        return this.version.compareTo(o.version);
    }
}
