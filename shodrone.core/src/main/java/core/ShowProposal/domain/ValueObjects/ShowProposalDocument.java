package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

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
    String documentContent;

    /**
     * The file path where the document is stored.
     * This can be used to retrieve the document from a file system or cloud storage.
     */
    String filePath;

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
     * @param filePath        The file path where the document is stored.
     * @throws IllegalArgumentException if documentContent is null or empty.
     */
    public ShowProposalDocument(String documentContent, String filePath) {
        if (documentContent == null || documentContent.isEmpty()) {
            throw new IllegalArgumentException("Document content cannot be null or empty");
        }
        this.documentContent = documentContent;
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        this.filePath = filePath;
    }

    /**
     * Adds a file path to the document.
     *
     */
    public void addFilePath(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        this.filePath = filePath;
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
