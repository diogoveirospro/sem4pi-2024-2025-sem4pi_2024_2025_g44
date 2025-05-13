package core.ShowRequest.domain.ValueObjects;

import eapli.framework.validations.Preconditions;

public class ShowDescription {
    private String description;

    public ShowDescription(String description) {
        Preconditions.nonEmpty(description, "description should neither be null nor empty");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
