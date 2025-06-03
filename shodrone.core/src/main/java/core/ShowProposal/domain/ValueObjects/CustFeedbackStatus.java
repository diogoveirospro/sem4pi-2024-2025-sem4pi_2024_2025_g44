package core.ShowProposal.domain.ValueObjects;

public enum CustFeedbackStatus {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    PENDING("Pending");
    private final String status;

    CustFeedbackStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
