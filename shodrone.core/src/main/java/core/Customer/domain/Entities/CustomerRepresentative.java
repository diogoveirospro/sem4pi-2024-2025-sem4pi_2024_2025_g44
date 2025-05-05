package core.Customer.domain.Entities;

import core.Customer.domain.ValueObjects.Position;
import core.Customer.domain.ValueObjects.CustomerRepresentativeStatus;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CustomerRepresentative implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Email email;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Position position;

    @Enumerated(EnumType.STRING)
    private CustomerRepresentativeStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    protected CustomerRepresentative() {
        // for ORM
    }

    public CustomerRepresentative(Name name, Email email, PhoneNumber phoneNumber, Position position, Customer customer) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.customer = customer;
        this.status = CustomerRepresentativeStatus.ACTIVE;
    }

    public Customer representee() {
        return customer;
    }

    public Long id() {
        return id;
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
    public Position position() {
        return position;
    }
    public CustomerRepresentativeStatus status() {
        return status;
    }

    public void changeInfo(Email newEmail, PhoneNumber newPhone) {
        this.email = newEmail;
        this.phoneNumber = newPhone;
    }

    public void deactivate() {
        this.status = CustomerRepresentativeStatus.DISABLE;
    }

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
}