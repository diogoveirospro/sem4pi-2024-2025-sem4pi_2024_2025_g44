package Shodrone.requests;

public class getShowsOfCustomerRequest {
    private final String vatNumber;

    public getShowsOfCustomerRequest(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String toRequest() {
        return "GET_SHOWS " + vatNumber;
    }

    public String vatNumber() {
        return vatNumber;
    }
}
