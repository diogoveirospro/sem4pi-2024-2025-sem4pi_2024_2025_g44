package core.Figure.domain.Entities;

import core.Customer.domain.Entities.Customer;
import core.Shared.domain.ValueObjects.Time;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.time.domain.model.DateInterval;
import jakarta.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Represents an exclusivity of Figure in the system.
 * This class is immutable and implements DomainEntity and Serializable interfaces.
 */
@Entity
public class Exclusivity implements DomainEntity<Long>, Serializable {

    /**
     * Serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The ID of the Exclusivity in the database
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The version of the Exclusivity in the database
     */
    @Version
    private Long version;

    /**
     * The customer associated with the exclusivity
     */
    @ManyToOne
    private Customer customer;

    /**
     * The duration of the exclusivity
     */
    @Embedded
    private DateInterval duration;

    /**
     * Default constructor for ORM
     */
    protected Exclusivity() {}

    /**
     * Constructor to create an Exclusivity object.
     *
     * @param customer the customer associated with the exclusivity
     * @param duration the duration of the exclusivity
     */
    public Exclusivity(Customer customer, DateInterval duration) {
        if (customer == null || duration == null) {
            throw new IllegalArgumentException("Customer and Duration cannot be null");
        }
        this.customer = customer;
        this.duration = duration;
    }

    /**
     * Gets the customer associated with the exclusivity.
     *
     * @return the customer associated with the exclusivity
     */
    public Customer customer() {
        return customer;
    }

    /**
     * Gets the duration of the exclusivity.
     *
     * @return the duration of the exclusivity
     */
    public DateInterval duration() {
        return duration;

    }

    /**
     * Gets the start time of the exclusivity.
     *
     * @return the start time of the exclusivity
     */
    public String startTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(duration.start().getTime());
    }

    /**
     * Gets the end time of the exclusivity.
     *
     * @return the end time of the exclusivity
     */
    public String endTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(duration.end().getTime());
    }

    /**
     * Checks if the exclusivity is the same as another object.
     * @param other the object to compare
     * @return true if the exclusivity is the same as the other object, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof Exclusivity)) return false;
        Exclusivity that = (Exclusivity) other;
        return this.customer.equals(that.customer) && this.duration.equals(that.duration);
    }

    /**
     * Gets the ID of the exclusivity.
     *
     * @return the ID of the exclusivity
     */
    @Override
    public Long identity() {
        return this.id;
    }

    /**
     * Checks if the exclusivity has the same identity as another exclusivity.
     * @param o the object to compare
     * @return true if the exclusivity has the same identity as the other object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exclusivity)) return false;
        Exclusivity that = (Exclusivity) o;
        return this.customer.equals(that.customer) && this.duration.equals(that.duration);
    }

    /**
     * Hash code method to generate a hash code for the Exclusivity object.
     * @return the hash code for the Exclusivity object
     */
    @Override
    public int hashCode() {
        return Objects.hash(customer, duration);
    }
}
