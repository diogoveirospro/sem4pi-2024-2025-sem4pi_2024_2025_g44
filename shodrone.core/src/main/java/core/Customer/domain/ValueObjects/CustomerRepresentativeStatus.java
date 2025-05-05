package core.Customer.domain.ValueObjects;

public enum CustomerRepresentativeStatus {
    /**
     * The customer representative is active and can use the system (upon creation)
     */
    ACTIVE("Active"),

    /**
     * The customer representative is disabled and can not use the system
     */
    DISABLE("Disable");

    private final String status;

    CustomerRepresentativeStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
