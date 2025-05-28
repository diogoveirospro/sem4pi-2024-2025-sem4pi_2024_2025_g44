package core.ShowRequest.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShowRequestID implements Comparable<ShowRequestID>, Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private String showRequestID;

    protected ShowRequestID () {
        // Default constructor for JPA
    }

    public ShowRequestID(String showRequestID) {
        if (showRequestID == null || !showRequestID.startsWith("REQ-")) {
            throw new IllegalArgumentException("ShowRequestID must not be null, empty, and must start with 'REQ-'");
        }
        this.showRequestID = showRequestID;
    }

    public String showRequestID() {
        return showRequestID;
    }


    @Override
    public int compareTo(ShowRequestID o) {
        return this.showRequestID().compareTo(o.showRequestID());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ShowRequestID that = (ShowRequestID) obj;
        return this.showRequestID.equals(that.showRequestID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showRequestID);
    }

    @Override
    public String toString() {
        return this.showRequestID;
    }
}

