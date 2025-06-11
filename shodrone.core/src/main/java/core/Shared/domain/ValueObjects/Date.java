package core.Shared.domain.ValueObjects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Date implements Serializable {

    private LocalDate value;

    protected Date() {

    }

    public Date(LocalDate value) {
        if (value == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.value = value;
    }

    public LocalDate value() {
        return value;
    }

    public boolean isBefore(Date other) {
        return this.value.isBefore(other.value);
    }

    public boolean isAfter(Date other) {
        return this.value.isAfter(other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return Objects.equals(value, date.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
