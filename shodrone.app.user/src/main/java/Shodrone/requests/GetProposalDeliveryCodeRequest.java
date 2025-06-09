package Shodrone.requests;

public class GetProposalDeliveryCodeRequest {
    private final String proposalNumber;


    public GetProposalDeliveryCodeRequest(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String toRequest() {
        return "GET_SHOWS " + proposalNumber;
    }

    public String proposalNumber() {
        return proposalNumber;
    }
}
