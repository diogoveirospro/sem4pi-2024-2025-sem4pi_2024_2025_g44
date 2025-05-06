package core.Figure.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Represents a version in the format X.Y.Z.
 * This class is immutable and implements Comparable, Serializable, and ValueObject interfaces.
 */
@Embeddable
public class Version implements Comparable<Version>, Serializable, ValueObject {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "figureVersion")
    private String value;

    /**
     * Default constructor for JPA.
     */
    protected Version() {
        this.value = null;
    }

    /**
     * Constructor to create a Version object.
     *
     * @param version the version string
     */
    public Version(final String version) {
        if (version == null || version.isEmpty()) {
            throw new IllegalArgumentException("Version cannot be null or empty");
        }

        if (!version.matches("^\\d+\\.\\d+\\.\\d+$")) {
            throw new IllegalArgumentException("Version must follow the format X.Y.Z (e.g., 1.0.0)");
        }

        this.value = version;
    }

    /**
     * Compares this Version object with another Version object.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this Version is less than, equal to, or greater than
     * the specified Version.
     */
    @Override
    public int compareTo(Version o) {
        if (o == null) {
            return 1;
        }
        return this.value.compareTo(o.value);
    }

    /**
     * Returns the string representation of this Version object.
     * @return the string representation of this Version object.
     */
    @Override
    public String toString(){
        return this.value;
    }

    /**
     * Checks if this Version object is equal to another object.
     * @param other the object to be compared.
     * @return true if this Version object is equal to the specified object; false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Version version1 = (Version) other;

        return value.equals(version1.value);
    }

    /**
     * Returns the hash code value for this Version object.
     * @return the hash code value for this Version object.
     */
    @Override
    public int hashCode(){
        int PRIME = 59;
        return value.hashCode() * PRIME;
    }
}
