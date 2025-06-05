package Server.protocol;

import core.Customer.domain.Entities.Customer;
import core.Daemon.customerApp.Controller.UserAppServerController;

public class GetCustomerRequest extends UserAppRequest {
    private final String repEmail;

    public GetCustomerRequest(final UserAppServerController controller, final String inputRequest, final String repEmail) {
        super(controller, inputRequest);
        this.repEmail = repEmail;
    }

    @Override
    public String execute() {
        try {
            Customer customer = controller.getCustomerByRepresentativeEmail(repEmail);
            if (customer == null) {
                return buildBadRequest("Customer not found for representative email: " + repEmail);
            }
            return buildResponse(customer);
        } catch (Exception e) {
            return buildServerError("An error occurred while processing the request");
        }
    }

    private String buildResponse(Customer customer) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"COMPANY_NAME\", \"VAT_NUMBER\"\n");
        sb.append("\"").append(customer.name()).append("\", ")
                .append("\"").append(customer.vat()).append("\"\n");
        return sb.toString();
    }
}
