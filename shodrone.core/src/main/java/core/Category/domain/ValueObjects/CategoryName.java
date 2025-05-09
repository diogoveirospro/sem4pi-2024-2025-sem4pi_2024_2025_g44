package core.Category.domain.ValueObjects;


import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the name of a category.
 * This class is immutable and implements ValueObject interface.
 */
@Embeddable
public class CategoryName implements Comparable<CategoryName>, ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The name of the category.
     */
    @Column(name = "name")
    private Name name;

    /**
     * Default constructor for JPA.
     */
    protected CategoryName() {
        this.name = null;
    }

    /**
     * Constructor to create a CategoryName object.
     * @param name the name of the category
     */
    public CategoryName(Name name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Returns the name of the category.
     */
    @Override
    public String toString() {
        return String.valueOf(name);
    }

    /**
     * equals method to compare two CategoryName objects.
     *
     * @param other the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        CategoryName that = (CategoryName) other;
        return name.equals(that.name);
    }

    /**
     * hashCode method to generate a hash code for the CategoryName object.
     *
     * @return the hash code of the CategoryName object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Compares this CategoryName object with another CategoryName object.
     *
     * @param o the CategoryName object to compare with
     * @return 0 if they are equal, a negative number if this object is less than the other, and a positive number if this object is greater than the other
     */
    @Override
    public int compareTo(CategoryName o) {
        return this.name.compareTo(o.name);
    }
}
