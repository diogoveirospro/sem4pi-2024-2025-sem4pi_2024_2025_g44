package core.Category.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;

public enum CategoryStatus implements ValueObject, Serializable {
    ACTIVE,
    INACTIVE,
}
