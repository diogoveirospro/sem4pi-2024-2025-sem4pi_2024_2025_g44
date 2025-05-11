package core.Customer.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.List;

@Embeddable
public class Position extends Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private List<Integer> position;

    protected Position() {
        // for ORM
    }

    public Position(List<Integer> position) {
        this.position = position;
    }


    public List<Integer> position() {
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
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }

}