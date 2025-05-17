package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShowRequestID implements Comparable<ShowRequestID>, Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private String identifier;

    protected ShowRequestID() {}

    public ShowRequestID(String customer, String collaborator, String date, String time) {
        Preconditions.nonEmpty(customer);
        Preconditions.nonEmpty(collaborator);
        Preconditions.nonEmpty(date);
        Preconditions.nonEmpty(time);
        this.identifier = "customer vatNumber = " + customer + " | collaborator email = " + collaborator + " | date = " + date + " | time = " + time;
    }

    @Override
    public int compareTo(ShowRequestID o) {
        if (this == o) return 0;
        if (o == null) return 1;

        return this.identifier.compareTo(o.identifier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowRequestID that = (ShowRequestID) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return identifier;
    }
}

