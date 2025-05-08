package core.Customer.domain.Entities;

import core.Customer.domain.ValueObjects.Position;
import core.Customer.domain.ValueObjects.CustomerRepresentativeStatus;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CustomerRepresentative implements Serializable, DomainEntity<EmailAddress> {

    /**
     * Serial version UID for serialization.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name of the customer representative.
     */
    @Embedded
    @Column(unique = true, nullable = false)
    private Name name;

    /**
     * The email of the customer representative.
     */
    @Embedded
    @Column(unique = true, nullable = false)
    private Email email;

    /**
     * The phone number of the customer representative.
     */
    @Embedded
    @Column(unique = true, nullable = false)
    private PhoneNumber phoneNumber;

    /**
     * The position of the customer representative.
     */
    @Embedded
    private Position position;

    /**
     * The status of the customer representative.
     */
    @Enumerated(EnumType.STRING)
    private CustomerRepresentativeStatus status;

    /**
     * The customer represented by the customer representative.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Default constructor for JPA.
     */
    protected CustomerRepresentative() {
        // for ORM
    }

    /**
     * Constructor for creating a new customer representative.
     *
     * @param name         the name of the customer representative
     * @param email        the email of the customer representative
     * @param phoneNumber  the phone number of the customer representative
     * @param position     the position of the customer representative
     * @param customer     the customer represented by the customer representative
     */
    public CustomerRepresentative(Name name, Email email, PhoneNumber phoneNumber, Position position, Customer customer) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.customer = customer;
        this.status = CustomerRepresentativeStatus.ACTIVE;
    }

    /**
     * The customer represented by the customer representative.
     * @return the customer represented by the customer representative
     */
    public Customer representee() {
        return customer;
    }
    /**
     * The ID of the customer representative.
     * @return the ID of the customer representative
     */
    public Long id() {
        return id;
    }

    /**
     * The name of the customer representative.
     * @return the name of the customer representative
     */
    public Name name() {
        return name;
    }

    /**
     * The email of the customer representative.
     * @return the email of the customer representative
     */
    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }
    /**
     * The email of the customer representative.
     * @return the email of the customer representative
     */
    public Email email() {
        return email;
    }
    /**
     * The position of the customer representative.
     * @return the position of the customer representative
     */
    public Position position() {
        return position;
    }
    /**
     * The status of the customer representative.
     * @return the status of the customer representative
     */
    public CustomerRepresentativeStatus status() {
        return status;
    }


    /**
     * Changes the email and phone number of the customer representative.
     * @param newEmail the new email of the customer representative
     * @param newPhone the new phone number of the customer representative
     */
    public void changeInfo(Email newEmail, PhoneNumber newPhone) {
        this.email = newEmail;
        this.phoneNumber = newPhone;
    }

    /**
     * Deactivates the customer representative.
     */
    public void deactivate() {
        this.status = CustomerRepresentativeStatus.DISABLE;
    }

    /**
     * Method to check if two customer representatives are equal.
     * @param o the object to be compared
     * @return true if the two customer representatives are equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerRepresentative)) return false;

        CustomerRepresentative that = (CustomerRepresentative) o;

        if (!name.equals(that.name)) return false;
        if (!email.equals(that.email)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        if (!position.equals(that.position)) return false;
        return status == that.status;
    }

    /**
     * Method to see if two customer representatives are the same.
     * @param other the object to be compared
     * @return true if the two customer representatives are the same, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        CustomerRepresentative that = (CustomerRepresentative) other;
        return email != null && email.equals(that.email);
    }

    /**
     * Method to get the identity of the customer representative.
     * @return the email of the customer representative
     */
    @Override
    public Email identity() {
        return email;
    }
}