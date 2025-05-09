package core.Category.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class IDCategory implements Comparable<IDCategory>, ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The ID of the category.
     */
    @Column(name = "id")
    private String value;

    /**
     * Default constructor for JPA.
     */
    protected IDCategory() {this.value = null;}

    /**
     * Constructor to create an IDCategory object.
     * @param id the id of the category
     */
    public IDCategory(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.value = id;
    }

    /**
     * ID of the category.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * equals method to compare two IDCategory objects.
     *
     * @param other the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        IDCategory that = (IDCategory) other;
        return value.equals(that.value);
    }


    /**
     * hashCode method to generate a hash code for the IDCategory object.
     *
     * @return the hash code of the IDCategory object
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    /**
     * Compares this IDCategory object with another IDCategory object.
     *
     * @param o the IDCategory object to compare with
     * @return 0 if they are equal, a negative number if this object is less than the other, and a positive number if this object is greater than the other
     */
    @Override
    public int compareTo(IDCategory o) {
        return 0;
    }
}
