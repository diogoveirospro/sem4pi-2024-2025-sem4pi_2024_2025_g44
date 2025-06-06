package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Location implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    double latitude;

    double longitude;

    double altitude;

    protected Location(){}

    public Location(double latitude, double longitude, double altitude) {
        Preconditions.noneNull(latitude, longitude, altitude);
        Preconditions.ensure(latitude >= -90 && latitude <= 90, "Latitude must be between -90 and 90 degrees");
        Preconditions.ensure(longitude >= -180 && longitude <= 180, "Longitude must be between -180 and 180 degrees");
        Preconditions.ensure(altitude >= 0, "Altitude must be non-negative");
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 &&
               Double.compare(location.longitude, longitude) == 0 &&
               Double.compare(location.altitude, altitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, altitude);
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude + ", " + altitude;
    }
}
