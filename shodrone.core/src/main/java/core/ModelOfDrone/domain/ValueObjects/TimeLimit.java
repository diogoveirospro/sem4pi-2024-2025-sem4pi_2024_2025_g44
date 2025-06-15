package core.ModelOfDrone.domain.ValueObjects;

import core.Drone.application.Service.DurationToLongConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

@Embeddable
public class TimeLimit implements Serializable {

    @Convert(converter = DurationToLongConverter.class)
    private Duration timeLimit;

    protected TimeLimit() {

    }

    public TimeLimit(Duration timeLimit) {
        if (timeLimit == null || timeLimit.isNegative() || timeLimit.isZero()) {
            throw new IllegalArgumentException("Time limit must be a positive duration.");
        }
        this.timeLimit = timeLimit;
    }

    public Duration value() {
        return timeLimit;
    }

    public boolean isExceededBy(Duration usageTime) {
        return usageTime.compareTo(timeLimit) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeLimit)) return false;
        TimeLimit that = (TimeLimit) o;
        return Objects.equals(timeLimit, that.timeLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeLimit);
    }

    @Override
    public String toString() {
        long hours = timeLimit.toHours();
        long minutes = timeLimit.minusHours(hours).toMinutes();
        return String.format("%02dH:%02dM", hours, minutes);
    }



}
