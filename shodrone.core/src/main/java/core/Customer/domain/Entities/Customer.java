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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer implements Serializable, AggregateRoot<VatNumber> {

    /**
     * Serial version UID for serialization.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The version of the Customer in the database
     */
    @Embedded
    @Column(unique = true, nullable = false)
    private Name name;

    /**
     * The address of the Customer
     */
    @Embedded
    @Column(unique = true, nullable = false)
    private Address address;

    /**
     * The VAT number of the Customer
     */
    @Embedded
    @Column(unique = true, nullable = false)
    private VatNumber vat;

    /**
     * The status of the Customer
     */
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    /**
     * The type of the Customer
     */
    @Enumerated(EnumType.STRING)
    private CustomerType type;

    /**
     * The list of customer representatives
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CustomerRepresentative> representatives;

    /**
     * Default constructor for ORM.
     */
    protected Customer() {
        // for ORM
    }

    /**
     * Constructor to create a Customer object.
     *
     * @param name the name of the customer
     * @param address the address of the customer
     * @param vat the VAT number of the customer
     * @param type the type of the customer
     */
    public Customer(Name name, Address address, VatNumber vat, CustomerType type) {
        this.name = name;
        this.address = address;
        this.vat = vat;
        this.status = CustomerStatus.CREATED;
        this.type = type;
        this.representatives = new ArrayList<>();
    }


    /**
     * Returns the name of the customer.
     * @return the name of the customer
     */
    public Name name(){
        return name;
    }

    /**
     * Returns the address of the customer.
     * @return the address of the customer
     */
    public Address address(){
        return address;
    }

    /**
     * Returns the VAT number of the customer.
     * @return the VAT number of the customer
     */
    public VatNumber vat(){
        return vat;
    }

    /**
     * Returns the status of the customer.
     * @return the status of the customer
     */
    public CustomerStatus status(){
        return status;
    }

    /**
     * Returns the type of the customer.
     * @return the type of the customer
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return  Objects.equals(name, customer.name) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(vat, customer.vat) &&
                status == customer.status &&
                type == customer.type &&
                Objects.equals(representatives, customer.representatives);
    }

    /**
     * Hash code method to generate a hash code for the Customer object.
     *
     * @return the hash code for the Customer object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, address, vat, status, type, representatives);
    }

    /**
     * Find a customer representative in the list of representatives
     *
     * @param representative the customer representative to find
     * @return the found customer representative, or null if not found
     */
    public CustomerRepresentative findCustomerRepresentative(CustomerRepresentative representative) {
        for (CustomerRepresentative rep : representatives) {
            if (rep.equals(representative)) {
                return rep;
            }
        }
        return null;
    }
}