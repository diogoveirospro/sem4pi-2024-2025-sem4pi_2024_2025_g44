package core.User.domain.Entities;

import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

@Entity
public class ShodroneUser implements AggregateRoot<Username> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Email email;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "SYSTEMUSER_USERNAME", referencedColumnName = "USERNAME")
    private SystemUser systemUser;


    public ShodroneUser(final SystemUser user, final PhoneNumber phoneNumber) {
        Preconditions.noneNull(phoneNumber, user);

        this.systemUser = user;
        this.email = new Email(user.email().toString());
        this.phoneNumber = phoneNumber;
    }

    protected ShodroneUser() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Email email() {
        return email;
    }

    public void changeEmail(Email email) {
        this.email = email;
    }

    public void changePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Username identity() {
        return this.systemUser.username();
    }
}

