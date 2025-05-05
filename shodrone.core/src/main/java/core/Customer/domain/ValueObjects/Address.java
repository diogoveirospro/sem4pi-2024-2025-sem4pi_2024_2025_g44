package core.Customer.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Address extends Designation implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    protected Address() {
    }

    public Address(String address) {
        super(validated(address));
    }

    private static String validated(String address) {
        return Designation.valueOf(address).toString();
    }
}
