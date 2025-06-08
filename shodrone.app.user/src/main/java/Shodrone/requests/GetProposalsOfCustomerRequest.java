package Shodrone.requests;

/**
 * Represents a request to send feedback on a proposal.
 * This request includes the proposal number, decision, and feedback text.
 */
public class GetProposalsOfCustomerRequest {

    /**
     * The VAT number of the customer for whom proposals are requested.
     */
    private final String vatNumber;

    /**
     * Constructs a new GetProposalsOfCustomerRequest with the specified VAT number.
     *
     * @param vatNumber The VAT number of the customer.
     */
    public GetProposalsOfCustomerRequest(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    /**
     * Converts this request to a string representation suitable for sending over a network.
     *
     * @return A string representation of the request.
     */
    public String toRequest() {
        return "GET_PROPOSALS " + vatNumber;
    }

    /**
     * Gets the VAT number associated with this request.
     *
     * @return The VAT number of the customer.
     */
    public String vatNumber() {
        return vatNumber;
    }
}
