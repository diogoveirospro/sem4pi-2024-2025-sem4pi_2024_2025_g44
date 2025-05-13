package core.Customer.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Position extends Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private final String position;

    protected Position() {
        // for ORM
        super(null);
        this.position = null;
    }

    public Position(String position) {
        super(validated(position));
        this.position = position;
    }

    private static String validated(String position) {
        return Designation.valueOf(position).toString();
    }

    public String position() {
        return position;
    }

    @Override
    public String toString() {
        return position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        final Position that = (Position) o;
        return this.position != null && this.position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}