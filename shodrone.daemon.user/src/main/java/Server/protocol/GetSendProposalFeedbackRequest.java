package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;

public class GetSendProposalFeedbackRequest extends UserAppRequest{
    private final String proposalNumber;
    private final String decision;
    private final String feedback;

    public GetSendProposalFeedbackRequest(final UserAppServerController controller, final String inputRequest,
                                          String proposalNumber, String decision, String feedback) {
        super(controller, inputRequest);
        this.proposalNumber = proposalNumber;
        this.decision = decision;
        this.feedback = feedback;
    }

    @Override
    public String execute() {
        try {
            if (proposalNumber == null || proposalNumber.isBlank()) {
                return buildBadRequest("Invalid Proposal Number");
            }

            if (decision == null || decision.isBlank()) {
                return buildBadRequest("Invalid Decision");
            }

            boolean response = controller.handleProposalFeedback(proposalNumber, decision, feedback);

            if (response) {
                return "Feedback sent successfully";
            }
            return "Failed to send feedback";
        } catch (Exception e) {
            return buildServerError(e.getMessage());
        }
    }
}
