package Shodrone.requests;

/**
 * Represents a request to send feedback on a proposal.
 * This request includes the proposal number, decision, and feedback text.
 */
public class SendFeedbackProposalRequest {

    /**
     * The unique identifier for the proposal.
     */
    private final String proposalNumber;

    /**
     * The decision made on the proposal, such as ACCEPTED or REJECTED.
     */
    private final String decision;

    /**
     * The feedback text provided for the proposal.
     */
    private final String feedback;

    /**
     * Constructs a new SendFeedbackProposalRequest.
     *
     * @param proposalNumber the unique identifier for the proposal
     * @param decision       the decision on the proposal (e.g., ACCEPTED, REJECTED)
     * @param feedback       the feedback text to provide on the proposal
     */
    public SendFeedbackProposalRequest(String proposalNumber, String decision, String feedback) {
        this.proposalNumber = proposalNumber;
        this.decision = decision;
        this.feedback = feedback;
    }

    /**
     * Converts the request to a string format suitable for sending over a protocol.
     *
     * @return a string representation of the request
     */
    public String toRequest() {
        return "SEND_FEEDBACK_PROPOSAL " + proposalNumber + " " + decision + " \"" + feedback + "\"";
    }
    /**
     * Gets the proposal number associated with this request.
     *
     * @return the proposal number
     */
    public String proposalNumber() {
        return proposalNumber;
    }

    /**
     * Gets the decision made on the proposal.
     *
     * @return the decision (e.g., ACCEPTED, REJECTED)
     */
    public String decision() {
        return decision;
    }

    /**
     * Gets the feedback text provided for the proposal.
     *
     * @return the feedback text
     */
    public String feedback() {
        return feedback;
    }
}
