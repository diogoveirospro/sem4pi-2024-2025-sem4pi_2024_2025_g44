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
}
