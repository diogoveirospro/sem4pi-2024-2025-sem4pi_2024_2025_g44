package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.time.domain.model.TemporalEvent;
import eapli.framework.util.HashCoder;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.UnaryOperator;

@Embeddable
public class Time<T> implements TemporalEvent<LocalDate, T>, ValueObject {

    private static final long serialVersionUID = 1L;
    private LocalDate date;
    private T value;

    protected Time() {}

    public Time(LocalDate date, T value) {
        if (date == null || value == null)
            throw new IllegalArgumentException("Both date and value are required");
        this.date = date;
        this.value = value;
    }

    @Override
    public LocalDate occurredAt() {
        return date;
    }

    @Override
    public T event() {
        return value;
    }

    @Override
    public TemporalEvent<LocalDate, T> shift(UnaryOperator<LocalDate> adjuster) {
        return new Time<>(adjuster.apply(date), value);
    }

    @Override
    public String toString() {
        return "Time{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Time)) return false;
        Time that = (Time) other;
        return Objects.equals(this.date, that.date) &&
                Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return new HashCoder().with(date).with(value).code();
    }
}
