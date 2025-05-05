package core.Figure.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.Locale;

/**
 * Represents a keyword for a figure.
 * This class is immutable and implements ValueObject interface.
 */
@Embeddable
public class Keyword implements Serializable, ValueObject, Comparable<Keyword> {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    String value;

    /**
     * Default constructor for JPA.
     */
    protected Keyword() {
        this.value = null;
    }

    /**
     * Constructor to create a Keyword object.
     *
     * @param value the keyword value
     */
    public Keyword(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Keyword cannot be null or blank");
        }

        value = value.trim();

        if (value.length() < 2 || value.length() > 30) {
            throw new IllegalArgumentException("Keyword must be between 2 and 30 characters");
        }

        if (!value.matches("[\\p{L}\\p{N}\\-]+")) {
            throw new IllegalArgumentException("Keyword can only contain letters, numbers and dashes");
        }

        this.value = value;
    }

    /**
     * Compares this Keyword object with another Keyword object.
     * @param other the object to be compared.
     * @return 0 if they are equal, a negative number if this object is less than the other, and a positive number if
     * this object is greater than the other.
     */
    @Override
    public int compareTo(Keyword other) {
        if (other == null) return 1;
        return normalize(this.value).compareTo(normalize(other.value));
    }

    /**
     * Normalizes the input string by removing accents and converting to lowercase.
     * @param input the input string to be normalized
     * @return the normalized string
     */
    private String normalize(String input) {
        String noAccents = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return noAccents.toLowerCase(Locale.ROOT);
    }

    /**
     * Returns the keyword value.
     * @return the keyword value
     */
    @Override
    public String toString(){
        return value;
    }

    /**
     * Checks if this Keyword object is equal to another object.
     * @param o the object to be compared
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Keyword)) return false;

        Keyword other = (Keyword) o;
        return normalize(value).equals(normalize(other.value));
    }

    /**
     * Hash code method to generate a hash code for the Keyword object.
     * @return the hash code for the Keyword object
     */
    @Override
    public int hashCode() {
        return normalize(value).hashCode();
    }
}
