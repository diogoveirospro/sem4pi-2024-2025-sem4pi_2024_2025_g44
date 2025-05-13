package core.Drone.domain.ValueObjects;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Embeddable
public class RemovalReason implements Serializable {

    @ElementCollection
    private Map<Date, String> reasons = new HashMap<>();

    protected RemovalReason() {
    }

    public RemovalReason(Map<Date, String> reasons) {
        this.reasons = reasons;
    }

    public Map<Date, String> value() {
        return reasons;
    }

    @Override
    public String toString() {
        return reasons.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemovalReason)) return false;
        RemovalReason that = (RemovalReason) o;
        return Objects.equals(reasons, that.reasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reasons);
    }

    public void addReason(String reason) {
        Date now = new Date();
        reasons.put(now ,reason);
    }
}

