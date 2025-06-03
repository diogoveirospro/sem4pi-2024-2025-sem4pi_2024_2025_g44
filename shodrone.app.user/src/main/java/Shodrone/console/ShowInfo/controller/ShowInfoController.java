package Shodrone.console.ShowInfo.controller;

import Shodrone.DTO.CustomerDTO;
import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.DTO.ShowDTO;
import Shodrone.Server.CustomerAppServer;
import Shodrone.exceptions.FailedRequestException;
import core.User.domain.Entities.ShodroneUser;
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
    private CustomerAppServer server;

    public Iterable<ShowDTO> listShows() throws FailedRequestException, IOException {
        CustomerDTO customer = getCustomerOfCurrentUser();
        return null;
    }

    private CustomerDTO getCustomerOfCurrentUser() throws FailedRequestException, IOException {
        SystemUser currentUser = authz.session().get().authenticatedUser();
        ShodroneUserDTO shodroneUser = server.getShodroneUser(currentUser.username().toString());
        return null;
    }

    public ShowDTO obtainShow(int selectedShow) {
        if (selectedShow < 0 || selectedShow >= shows.size()) {
            throw new IndexOutOfBoundsException("Invalid show selection");
        }
        return shows.get(selectedShow);
    }
}
