package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ShowRequestID implements Comparable<ShowRequestID>, Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private String identifier;

    protected ShowRequestID() {}

    public ShowRequestID(String customer, String collaborator, String date, String time) {
        this.identifier = customer + collaborator + date + time;
    }

    @Override
    public int compareTo(ShowRequestID o) {
        if (this == o) return 0;
        if (o == null) return 1;

        return this.identifier.compareTo(o.identifier);
    }
}

