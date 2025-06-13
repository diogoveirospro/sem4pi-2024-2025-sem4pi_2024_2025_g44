package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;
import core.User.domain.Entities.ShodroneUser;

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
            return buildResponse(response);
        } catch (Exception e) {
            return buildServerError(e.getMessage());
        }
    }

    private String buildResponse(final boolean success) {
        StringBuilder response = new StringBuilder();
        if (success) {
            response.append("FEEDBACK_EDITED, \"Feedback sent successfully\"\n");
        } else {
            response.append("FEEDBACK_EDITED_FAILED, \"Failed to send feedback\"\n");
        }
        return response.toString();
    }
}
