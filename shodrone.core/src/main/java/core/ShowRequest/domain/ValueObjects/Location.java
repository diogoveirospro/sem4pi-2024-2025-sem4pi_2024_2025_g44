package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Location implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private String location;

    protected Location(){}

    public Location(String location) {
        Preconditions.nonEmpty(location, "location should neither be null nor empty");
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
