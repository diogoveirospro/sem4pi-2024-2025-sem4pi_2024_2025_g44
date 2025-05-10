package core.Customer.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Address extends Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String address;

    protected Address() {
        // for ORM
    }

    public Address(String address) {
        super(validated(address));
        this.address = address;
    }

    private static String validated(String address) {
        return Designation.valueOf(address).toString();
    }

    public String address() {
        return address;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        final Address that = (Address) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }

    @Override
    public String toString() {
        return address;
    }
}