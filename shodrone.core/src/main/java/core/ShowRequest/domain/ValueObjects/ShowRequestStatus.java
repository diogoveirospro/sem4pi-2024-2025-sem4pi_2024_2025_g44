package core.ShowRequest.domain.ValueObjects;

public enum ShowRequestStatus {
    CREATED("Created"),
    ACCEPTED("Accepted");
    private final String status;

    ShowRequestStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
