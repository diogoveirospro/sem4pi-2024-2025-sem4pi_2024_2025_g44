package core.ShowProposal.domain.ValueObjects;

public enum CustomerFeedbackStatus {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    PENDING("Pending");
    private final String status;

    CustomerFeedbackStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
