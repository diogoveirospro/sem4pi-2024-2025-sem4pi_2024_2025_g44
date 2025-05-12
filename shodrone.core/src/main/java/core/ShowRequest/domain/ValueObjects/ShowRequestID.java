package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;
import java.util.Objects;

public class ShowRequestID implements Comparable<ShowRequestID>, Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private Long id;

    protected ShowRequestID() {}

    // TODO: find a way to do a counter to identify each SHOW REQUEST
    public ShowRequestID(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(ShowRequestID other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowRequestID that = (ShowRequestID) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

