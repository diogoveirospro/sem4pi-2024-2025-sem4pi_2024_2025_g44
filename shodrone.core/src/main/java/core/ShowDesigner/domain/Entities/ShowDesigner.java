package core.ShowDesigner.domain.Entities;

import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Represents a Show Designer in the system.
 * This class is immutable and implements AggregateRoot and Serializable interfaces.
 */
@Entity
public class ShowDesigner implements AggregateRoot<EmailAddress>, Serializable {

    /**
     * Serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The ID of the Show Designer
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The version of the Show Designer
     */
    @Version
    private Long version;

    /**
     * The name of the Show Designer
     */
    @Embedded
    private Name name;

    /**
     * The phone number of the Show Designer
     */
    @Embedded
    private PhoneNumber phoneNumber;

    /**
     * The email address of the Show Designer
     */
    @Embedded
    private Email email;

    /**
     * Default constructor for JPA
     *
     */
    protected ShowDesigner(){
        this.name = null;
        this.phoneNumber = null;
        this.email = null;
    }

    /**
     * Constructor for Show Designer
     *
     * @param name the name of the Show Designer
     * @param phoneNumber the phone number of the Show Designer
     * @param email the email address of the Show Designer
     */
    public ShowDesigner(Name name, PhoneNumber phoneNumber, Email email) {
        if (name == null || phoneNumber == null || email == null) {
            throw new IllegalArgumentException("Name, Phone Number and Email cannot be null");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Returns the name of the Show Designer
     *
     * @return the name of the Show Designer
     */
    public Name name() {
        return this.name;
    }

    /**
     * Returns the phone number of the Show Designer
     *
     * @return the phone number of the Show Designer
     */
    public PhoneNumber phoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns the email address of the Show Designer
     *
     * @return the email address of the Show Designer
     */
    public Email email() {
        return this.email;
    }

    /**
     * Verifies if the Show Designer is the same as another object
     *
     * @return true if the Show Designer is the same as the other object, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof ShowDesigner)) return false;
        ShowDesigner that = (ShowDesigner) other;
        return this.name.equals(that.name) &&
                this.phoneNumber.equals(that.phoneNumber) &&
                this.email.equals(that.email);
    }

    /**
     * Compares the Show Designer with another Show Designer
     *
     * @return 1 if this Show Designer is greater than the other, -1 if less, 0 if equal
     */
    @Override
    public int compareTo(EmailAddress other) {
        if (other == null) {
            return 1;
        }
        return this.email.compareTo(other);
    }

    /**
     * Returns the identity of the Show Designer
     *
     * @return the email address of the Show Designer
     */
    @Override
    public EmailAddress identity() {
        return this.email;
    }

    /**
     * Verifies if the Show Designer has the same identity as another Show Designer
     *
     * @return true if the Show Designer has the same identity as the other, false otherwise
     */
    @Override
    public boolean hasIdentity(EmailAddress id) {
        if (id == null) {
            return false;
        }
        return this.email.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }
}
