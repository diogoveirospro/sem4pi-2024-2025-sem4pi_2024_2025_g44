package core.Customer.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Position extends Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    protected Position() {
        // for ORM
    }

    public Position(String position) {
        super(validated(position));
    }

    private static String validated(String position) {
        return Designation.valueOf(position).toString();
    }
}
