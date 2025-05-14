package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Email extends EmailAddress implements ValueObject, Serializable, Comparable<EmailAddress> {

    private static final long serialVersionUID = 1L;

    private String email;

    protected Email() {

    }

    public Email(final String email) {
        super(validated(email));
        this.email = email;
    }

    private static String validated(String email) {
        return EmailAddress.valueOf(email).toString();
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Email)) {
            return false;
        }
        final Email that = (Email) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public int compareTo(EmailAddress o) {
        return this.email.compareTo(o.toString());
    }
}