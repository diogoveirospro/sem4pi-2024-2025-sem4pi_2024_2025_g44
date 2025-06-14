package core.Drone.domain.ValueObjects;

import core.Drone.repositories.DurationToLongConverter;
import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class UsageTime implements Serializable {

    @Convert(converter = DurationToLongConverter.class)
    private Duration usageTime;

    public UsageTime() {

    }

    public UsageTime(final Duration usageTime) {
        if (usageTime == null) {
            throw new IllegalArgumentException("Usage time must not be null");
        }
        this.usageTime = usageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsageTime)) return false;
        UsageTime that = (UsageTime) o;
        return usageTime.equals(that.usageTime);
    }

    @Override
    public int hashCode() {
        return usageTime.hashCode();
    }

    @Override
    public String toString() {
        long hours = usageTime.toHours();
        long minutes = usageTime.minusHours(hours).toMinutes();
        return String.format("%02d:%02d", hours, minutes);
    }

    public void addTime(LocalTime time) {
        if (time == null) {
            throw new IllegalArgumentException("Time to add must not be null");
        }

        if (time.getSecond() != 0 || time.getNano() != 0) {
            throw new IllegalArgumentException("Invalid time format. Only hours and minutes are allowed.");
        }

        /*if (time.getHour() < 0 || time.getMinute() < 0 || time.getHour() > 23 || time.getMinute() > 59) {
            throw new IllegalArgumentException("Invalid time value. Hours must be between 0 and 23, and minutes between 0 and 59.");
        }*/
       if (this.usageTime == null) {
           this.usageTime = Duration.ofHours(time.getHour()).plusMinutes(time.getMinute());
        }
        else {
        this.usageTime = this.usageTime.plusHours(time.getHour())
                .plusMinutes(time.getMinute());
        }
    }

    public void resetUsageTime() {
        this.usageTime = Duration.ZERO;
    }

}