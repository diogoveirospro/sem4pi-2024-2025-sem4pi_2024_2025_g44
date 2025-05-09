package core.ModelOfDrone.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ModelName extends Designation implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    protected ModelName() {
    }

    public ModelName(String name) {
        super(validated(name));
    }

    private static String validated(String name) {
        return Designation.valueOf(name).toString();
    }
}
