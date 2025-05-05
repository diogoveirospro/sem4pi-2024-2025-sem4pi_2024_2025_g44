package core.Customer.domain.ValueObjects;


public enum CustomerStatus {

    /**
     * The customer is active and can use the system (upon creation)
     */
    CREATED("Created"),

    /**
     * The customer is inactive and can not use the system
     */
    INACTIVE("Inactive"),
    /**
     * The Customer can not use the system
     */
    SUSPENDED("Suspended");

    private final String status;

    CustomerStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
