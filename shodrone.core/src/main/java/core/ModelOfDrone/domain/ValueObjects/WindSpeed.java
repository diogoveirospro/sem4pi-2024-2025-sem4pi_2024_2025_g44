package core.ModelOfDrone.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WindSpeed implements Serializable {
    private int minSpeed;
    private int maxSpeed;

    protected WindSpeed() {
        // For JPA
    }

    public WindSpeed(int minSpeed, int maxSpeed) {
        if (minSpeed < 0 || maxSpeed <= minSpeed) {
            throw new IllegalArgumentException("Invalid wind speed range.");
        }
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public int minSpeed() {
        return minSpeed;
    }

    public int maxSpeed() {
        return maxSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WindSpeed)) return false;
        WindSpeed that = (WindSpeed) o;
        return minSpeed == that.minSpeed && maxSpeed == that.maxSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minSpeed, maxSpeed);
    }

    @Override
    public String toString() {
        return minSpeed + " < wind <= " + maxSpeed;
    }

}
