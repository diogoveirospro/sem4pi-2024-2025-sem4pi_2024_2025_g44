package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Email extends EmailAddress implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public Email(final String address) {
        super(validated(address));
    }

    protected Email() {
        // for ORM
        super("");
    }

    public static String validated(String email) {
        return EmailAddress.valueOf(email).toString();
    }


}
