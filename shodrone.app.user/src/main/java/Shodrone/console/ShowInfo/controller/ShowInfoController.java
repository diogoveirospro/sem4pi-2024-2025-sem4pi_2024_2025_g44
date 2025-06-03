package Shodrone.console.ShowInfo.controller;

import Shodrone.DTO.ShowDTO;
import Shodrone.Server.CustomerAppServer;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

@UseCaseController
public class ShowInfoController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private List<ShowDTO> shows;
    private CustomerAppServer server;

    public Iterable<ShowDTO> listShows() {
        return null;
    }

    public ShowDTO obtainShow(int selectedShow) {
        if (selectedShow < 0 || selectedShow >= shows.size()) {
            throw new IndexOutOfBoundsException("Invalid show selection");
        }
        return shows.get(selectedShow);
    }
}
