package core.Customer.domain.ValueObjects;

public enum CustomerType {

    /**
     * The customer is a regular customer
     */
    REGULAR("Regular"),
    /**
     * The customer is a vip customer
     */
    VIP("VIP");

    private final String type;

    CustomerType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
