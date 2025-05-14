package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ShowDescription implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private String description;

    protected ShowDescription(){}

    public ShowDescription(String description) {
        Preconditions.nonEmpty(description, "description should neither be null nor empty");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
