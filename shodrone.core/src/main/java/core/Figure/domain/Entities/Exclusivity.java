package core.Figure.domain.Entities;

import core.Customer.domain.Entities.Customer;
import core.Shared.domain.ValueObjects.Time;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.time.domain.model.DateInterval;
import jakarta.persistence.*;

import java.io.Serializable;

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
}
