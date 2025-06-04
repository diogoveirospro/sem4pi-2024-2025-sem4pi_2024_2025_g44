package Shodrone.requests;

public class GetShodroneUserRequest {
    private final String username;

    public GetShodroneUserRequest(String username) {
        this.username = username;
    }

    public String toRequest() {
        return "GET_SHODRONE_USER " + username;
    }

    public String username() {
        return username;
    }
}