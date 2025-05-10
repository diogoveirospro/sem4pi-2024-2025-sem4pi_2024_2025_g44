package core.Drone.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SerialNumber extends Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String serialNumber;

    protected SerialNumber() {
        // for ORM
    }

    public SerialNumber(String serialNumber) {
        super(validated(serialNumber));
        this.serialNumber = serialNumber;
    }

    private static String validated(String serialNumber) {
        return Designation.valueOf(serialNumber).toString();
    }

    public String serialNumber() {
        return serialNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SerialNumber)) {
            return false;
        }
        final SerialNumber that = (SerialNumber) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return serialNumber != null ? serialNumber.hashCode() : 0;
    }

    @Override
    public String toString() {
        return serialNumber;
    }
}