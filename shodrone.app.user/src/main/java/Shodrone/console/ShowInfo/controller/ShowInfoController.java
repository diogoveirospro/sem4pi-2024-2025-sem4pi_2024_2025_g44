package Shodrone.console.ShowInfo.controller;

import Shodrone.DTO.CustomerDTO;
import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.DTO.ShowDTO;
import Shodrone.Server.CustomerAppProtocolProxy;
import Shodrone.exceptions.FailedRequestException;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;
import java.util.List;

@UseCaseController
public class ShowInfoController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private List<ShowDTO> shows;
    private final CustomerAppProtocolProxy server = new CustomerAppProtocolProxy();

    public Iterable<ShowDTO> listShows() throws FailedRequestException, IOException {
        CustomerDTO customer = getCustomerOfCurrentUser();
        return null;
    }

    private CustomerDTO getCustomerOfCurrentUser() throws FailedRequestException, IOException {
        SystemUser currentUser = authz.session().get().authenticatedUser();
        ShodroneUserDTO shodroneUser = server.getShodroneUser(currentUser.username().toString());
        if (shodroneUser == null) {
            throw new FailedRequestException("User not found");
        }
        CustomerDTO customer = server.getCustomers(shodroneUser.email);
        System.out.println("Customer: " + customer.companyName);
        return customer;
    }

    public ShowDTO obtainShow(int selectedShow) {
        if (selectedShow < 0 || selectedShow >= shows.size()) {
            throw new IndexOutOfBoundsException("Invalid show selection");
        }
        return shows.get(selectedShow);
    }
}
