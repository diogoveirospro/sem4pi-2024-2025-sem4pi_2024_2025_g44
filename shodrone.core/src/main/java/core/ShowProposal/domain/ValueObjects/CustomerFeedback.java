package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

/**
 * Represents customer feedback for a show proposal.
 */
@Embeddable
public class CustomerFeedback implements ValueObject, Serializable {

    /**
     * The serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The status of the customer feedback.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "feedbackStatus")
    private CustomerFeedbackStatus feedbackStatus;

    /**
     * The text of the customer feedback.
     */
    @Column(name="feedbackText")
    private String feedbackText;

    /**
     * Default constructor for ORM.
     */
    protected CustomerFeedback() {
        // for ORM
    }

    /**
     * Constructor to create a CustomerFeedback instance with specified status and text.
     * @param feedbackStatus the status of the feedback
     * @param feedbackText the text of the feedback
     */
    public CustomerFeedback(CustomerFeedbackStatus feedbackStatus, String feedbackText) {
        if (feedbackStatus == null || feedbackText == null || feedbackText.isBlank()) {
            throw new IllegalArgumentException("Feedback status and text cannot be null or empty");
        }
        this.feedbackStatus = feedbackStatus;
        this.feedbackText = feedbackText;
    }

    /**
     * Returns the status of the customer feedback.
     * @return the feedback status
     */
    public CustomerFeedbackStatus feedbackStatus() {
        return feedbackStatus;
    }

    /**
     * Returns the text of the customer feedback.
     * @return the feedback text
     */
    public String feedbackText() {
        return feedbackText;
    }
}
