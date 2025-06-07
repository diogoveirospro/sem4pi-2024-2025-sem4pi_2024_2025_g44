package Server.protocol;

import core.Daemon.customerApp.Controller.UserAppServerController;
import core.User.domain.Entities.ShodroneUser;

public class GetShodroneUserRequest extends UserAppRequest {
    private final String username;

    public GetShodroneUserRequest(final UserAppServerController controller, final String inputRequest, final String username) {
        super(controller, inputRequest);
        this.username = username;
    }

    @Override
    public String execute() {
        try {
            System.out.println("Executing GetShodroneUserRequest for username: " + username);
            ShodroneUser user = controller.getShodroneUserByUsername(username);
            if (user == null) {
                return buildBadRequest("User not found");
            }
            System.out.println("User found: " + user.user().username());
            return buildResponse(user);
        } catch (Exception e) {
            return buildServerError("An error occurred while processing the request");
        }
    }

    private String buildResponse(final ShodroneUser user) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"EMAIL\", \"USERNAME\", \"PHONE_NUMBER\"\n");
        sb.append("\"").append(user.email()).append("\", ")
                .append("\"").append(user.user().username()).append("\", ")
                .append("\"").append(user.phoneNumber()).append("\"\n");
        return sb.toString();
    }
}