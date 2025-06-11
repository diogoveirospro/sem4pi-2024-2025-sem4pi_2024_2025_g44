package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;

import java.io.Serializable;

/**
 * Represents a document associated with a show proposal.
 * This class is used to encapsulate the content of the document and its file path.
 * It is marked as embeddable for ORM purposes.
 */
@Embeddable
public class ShowProposalDocument implements ValueObject, Serializable {

    /**
     * The content of the document, typically in a string format.
     */
    private String documentContent;

    /**
     * The file associated with the document, stored as a byte array.
     * This allows for the storage of binary data such as images or other file types.
     */
    @Lob
    private byte[] file;

    /**
     * Default constructor for ORM purposes.
     * It is protected to prevent instantiation outside of the ORM context.
     */
    protected ShowProposalDocument() {
        // for ORM
    }

    /**
     * Constructs a ShowProposalDocument with the specified content and file path.
     *
     * @param documentContent The content of the document.
     * @param file        The file where the document is stored.
     * @throws IllegalArgumentException if documentContent is null or empty.
     */
    public ShowProposalDocument(String documentContent, byte[] file) {
        if (documentContent == null || documentContent.isEmpty()) {
            throw new IllegalArgumentException("Document content cannot be null or empty");
        }
        this.documentContent = documentContent;
        if (file == null || file.length == 0) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }
        this.file = file;
    }

    /**
     * Returns the file associated with the document.
     * @return The file as a byte array.
     */
    public byte[] file() {
        return file;
    }

    /**
     * Return toString representation of the document.
     *
     * @return String representation of the document content.
     */
    public String toString() {
        return documentContent;
    }
}
