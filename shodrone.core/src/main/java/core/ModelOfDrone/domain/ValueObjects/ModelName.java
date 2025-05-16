package core.ModelOfDrone.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ModelName extends Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String modelName;

    protected ModelName() {
        // for ORM
    }

    public ModelName(String modelName) {
        super(validated(modelName));
        this.modelName = modelName;
    }

    private static String validated(String modelName) {
        return Designation.valueOf(modelName).toString();
    }

    public String value() {
        return modelName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModelName)) {
            return false;
        }
        final ModelName that = (ModelName) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return modelName != null ? modelName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return modelName;
    }
}