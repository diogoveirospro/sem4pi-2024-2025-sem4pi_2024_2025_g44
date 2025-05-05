package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

@Embeddable
public class Name extends Designation implements ValueObject {

    private static final long serialVersionUID = 1L;

    protected Name() {
    }

    public Name(String name) {
        super(validated(name));
    }

    private static String validated(String name) {
        return Designation.valueOf(name).toString();
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
}
