package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CustomerFeedback implements ValueObject, Serializable {
    protected CustomerFeedback() {
        // for ORM
    }
}
