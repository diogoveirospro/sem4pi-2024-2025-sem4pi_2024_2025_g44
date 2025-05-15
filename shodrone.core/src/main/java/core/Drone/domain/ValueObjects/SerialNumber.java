package core.Drone.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SerialNumber extends Designation implements ValueObject, Serializable {


    private static final long serialVersionUID = 1L;

    private int serialNumber;

    protected SerialNumber() {
        // for ORM
    }

    public SerialNumber(int serialNumber) {
        if (serialNumber <= 0) {
            throw new IllegalArgumentException("Serial number must be a positive integer.");
        }
        this.serialNumber = serialNumber;
    }

    public int value() {
        return serialNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SerialNumber)) return false;
        SerialNumber that = (SerialNumber) o;
        return serialNumber == that.serialNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber);
    }

    @Override
    public String toString() {
        return String.valueOf(serialNumber);
    }
}