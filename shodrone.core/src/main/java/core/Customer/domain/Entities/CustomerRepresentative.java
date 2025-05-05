package core.Customer.domain.Entities;

import core.Customer.domain.ValueObjects.Position;
import core.Customer.domain.ValueObjects.CustomerRepresentativeStatus;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import jakarta.persistence.*;

@Entity
public class CustomerRepresentative {

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

    // Getters and setters omitted for brevity
}