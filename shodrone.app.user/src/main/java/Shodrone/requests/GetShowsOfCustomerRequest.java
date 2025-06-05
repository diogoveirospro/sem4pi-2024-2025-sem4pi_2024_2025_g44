package Shodrone.requests;

public class GetShowsOfCustomerRequest {
    private final String vatNumber;

    public GetShowsOfCustomerRequest(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String toRequest() {
        return "GET_SHOWS " + vatNumber;
    }

    public String vatNumber() {
        return vatNumber;
    }
}
