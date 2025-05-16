package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return Objects.equals(location, location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return location;
    }
}
