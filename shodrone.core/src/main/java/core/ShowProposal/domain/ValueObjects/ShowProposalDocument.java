package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ShowProposalDocument implements ValueObject, Serializable {

    String documentContent;

    protected ShowProposalDocument() {
        // for ORM
    }

    public ShowProposalDocument(String documentContent) {
        if (documentContent == null || documentContent.isEmpty()) {
            throw new IllegalArgumentException("Document content cannot be null or empty");
        }
        this.documentContent = documentContent;
    }

    public String toString() {
        return documentContent;
    }
}
