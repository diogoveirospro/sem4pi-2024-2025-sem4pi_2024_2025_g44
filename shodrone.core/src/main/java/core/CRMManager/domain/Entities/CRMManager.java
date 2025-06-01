package core.CRMManager.domain.Entities;

import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CRMManager implements AggregateRoot<EmailAddress>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    @Column(unique = true, nullable = false)
    private Email email;

    protected CRMManager() {
    }

    public CRMManager(Name name, PhoneNumber phoneNumber, Email email) {
        if (name == null || phoneNumber == null || email == null) {
            throw new IllegalArgumentException("Name, Phone Number and Email cannot be null");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Name name() {
        return name;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Email email() {
        return email;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof CRMManager)) return false;
        CRMManager that = (CRMManager) other;
        return this.name.equals(that.name()) &&
                this.phoneNumber.equals(that.phoneNumber()) &&
                this.email.equals(that.email());
    }

    @Override
    public int compareTo(EmailAddress other) {
        if (other == null) {
            return 1;
        }
        return this.email.compareTo(other);
    }

    @Override
    public EmailAddress identity() {
        return this.email;
    }

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
