package core.ShowRequest.domain.ValueObjects;

import eapli.framework.validations.Preconditions;

public class Location {
    private String location;

    public Location(String location) {
        Preconditions.nonEmpty(location, "location should neither be null nor empty");
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
