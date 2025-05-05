package core.Customer.domain.Entities;

import jakarta.persistence.Entity;

import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Customer.domain.ValueObjects.CustomerStatus;
import core.Customer.domain.ValueObjects.CustomerType;

import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Customer implements Serializable, AggregateRoot<VatNumber> {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Embedded
    private VatNumber vat;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CustomerRepresentative> representatives;

    protected Customer() {
        // for ORM
    }

    public Customer(Name name, Address address, VatNumber vat, CustomerType type) {
        this.name = name;
        this.address = address;
        this.vat = vat;
        this.status = CustomerStatus.CREATED;
        this.type = type;
    }

    public Name name(){
        return name;
    }

    public Address address(){
        return address;
    }

    public VatNumber vat(){
        return vat;
    }

    public CustomerStatus status(){
        return status;
    }

    public CustomerType type(){
        return type;
    }

    /**
     * @param other the object to compare with
     * @return true if the two objects are the same, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Customer customer = (Customer) other;
        return vat.equals(customer.vat);
    }

    /**
     * @return the identity of this object
     */
    @Override
    public VatNumber identity() {
        return vat;
    }

    /**
     * Add a customer representative to the customer
     */

    public void addCustomerRepresentative(CustomerRepresentative representative) {
        representatives.add(representative);
    }

    /**
     * Obtain the customer representatives
     *
     * @return the customer representatives
     */

    public List<CustomerRepresentative> representativesOfCustomer() {
        return representatives;
    }
}