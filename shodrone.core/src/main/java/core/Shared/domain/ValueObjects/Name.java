package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Name extends Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    protected Name() {
        // for ORM
    }

    public Name(String name) {
        super(validated(name));
        this.name = name;
    }

    private static String validated(String name) {
        return Designation.valueOf(name).toString();
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        final Name that = (Name) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}