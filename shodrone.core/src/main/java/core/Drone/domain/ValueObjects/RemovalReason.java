package core.Drone.domain.ValueObjects;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Embeddable
public class RemovalReason implements Serializable {

    @ElementCollection
    private Map<LocalDate, String> reasons = new LinkedHashMap<>();

    protected RemovalReason(String put) {
    }

    public RemovalReason(Map<LocalDate, String> reasons) {
        this.reasons = reasons;
    }

    public RemovalReason() {

    }

    public Map<LocalDate, String> value() {
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
        LocalDate now = LocalDate.now();
        reasons.put(now ,reason);
    }
}

