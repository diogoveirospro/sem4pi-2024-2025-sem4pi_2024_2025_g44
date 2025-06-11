package Shodrone.requests;

public class GetProposalByCodeRequest {
    private final String code;

    public GetProposalByCodeRequest(String code) {
        this.code = code;
    }

    public String toRequest() {
        return "GET_PROPOSAL_BY_CODE " + code;
    }

    public String code() {
        return code;
    }
}
