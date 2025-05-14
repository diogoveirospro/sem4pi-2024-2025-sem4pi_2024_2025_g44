package core.CRMCollaborator.domain.Entities;

import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class CRMCollaborator implements AggregateRoot<EmailAddress>, Serializable {

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
    private Email email;

    protected CRMCollaborator() {
    }

    public CRMCollaborator(Name name, PhoneNumber phoneNumber, Email email) {
        if (name == null || phoneNumber == null || email == null) {
            throw new IllegalArgumentException("Name, Phone Number and Email cannot be null");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof CRMCollaborator)) return false;
        CRMCollaborator that = (CRMCollaborator) other;
        return this.name.equals(that.getName()) &&
                this.phoneNumber.equals(that.getPhoneNumber()) &&
                this.email.equals(that.getEmail());
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
