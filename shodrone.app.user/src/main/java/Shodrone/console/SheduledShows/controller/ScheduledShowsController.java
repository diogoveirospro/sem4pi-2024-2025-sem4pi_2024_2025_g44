package Shodrone.console.SheduledShows.controller;


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

@UseCaseController
public class ScheduledShowsController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CustomerAppProtocolProxy server = new CustomerAppProtocolProxy();

    public Iterable<ShowDTO> listShows() throws FailedRequestException, IOException {
        CustomerDTO customer = getCustomerOfCurrentUser();
        Iterable<ShowDTO> list = server.getShows(customer.VatNumber);
        if (list == null) {
            throw new FailedRequestException("No shows found for the customer");
        }
        return list;
    }

    private CustomerDTO getCustomerOfCurrentUser() throws FailedRequestException, IOException {
        SystemUser currentUser = authz.session().get().authenticatedUser();
        ShodroneUserDTO shodroneUser = server.getShodroneUser(currentUser.username().toString());
        if (shodroneUser == null) {
            throw new FailedRequestException("User not found");
        }
        return server.getCustomerOfRepresentative(shodroneUser.email);
    }
}
