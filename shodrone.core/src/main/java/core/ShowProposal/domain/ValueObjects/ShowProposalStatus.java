package core.ShowProposal.domain.ValueObjects;

public enum ShowProposalStatus {

    ACCEPTED("Accepted"),
    READY_TO_SEND("Ready to send"),
    REJECTED("Rejected"),
    WAITING_FOR_RESPONSE("Waiting for response"),
    TESTING("Testing");

    private final String status;

    ShowProposalStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
