package core.Drone.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;

@Embeddable
public class SerialNumber extends Designation implements ValueObject, Serializable{

    private static final long serialVersionUID = 1L;

    protected SerialNumber() {
    }

    public SerialNumber(Integer serialNumber) {
        super(String.valueOf(validated(serialNumber)));
    }

    private static String validated(Integer serialNumber) {
        return Designation.valueOf(String.valueOf(serialNumber)).toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof core.Shared.domain.ValueObjects.Name)) {
            return false;
        }
        final core.Shared.domain.ValueObjects.Name that = (core.Shared.domain.ValueObjects.Name) o;
        return this.toString().equals(that.toString());
    }

}
