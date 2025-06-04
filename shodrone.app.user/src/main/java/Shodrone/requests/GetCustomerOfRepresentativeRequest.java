package Shodrone.requests;

public class GetCustomerOfRepresentativeRequest {
    private final String repEmail;

    public GetCustomerOfRepresentativeRequest(String repEmail) {
        this.repEmail = repEmail;
    }


    public String toRequest() {
        return "GET_CUSTOMER " + repEmail;
    }

    public String repEmail() {
        return repEmail;
    }
}
