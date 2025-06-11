package core.ShowProposal.domain.ValueObjects;

public enum ShowProposalStatus {

    ACCEPTED("Accepted"),
    READY_TO_SEND("Ready to Send"),
    REJECTED("Rejected"),
    WAITING_FOR_RESPONSE("Waiting for Response"),
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
