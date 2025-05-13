package core.ModelOfDrone.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PositionTolerance implements Serializable {
    private double tolerance;

    public PositionTolerance() {
    }

    public PositionTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public double value() {
        return tolerance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionTolerance)) return false;
        PositionTolerance that = (PositionTolerance) o;
        return Double.compare(that.tolerance, tolerance) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(tolerance);
    }

    @Override
    public String toString() {
        return String.valueOf(tolerance);
    }
}
