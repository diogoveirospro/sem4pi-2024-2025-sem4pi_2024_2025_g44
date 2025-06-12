package Shodrone.console.SheduledShows.ui;

import Shodrone.DTO.ShowDTO;
import Shodrone.console.SheduledShows.controller.ScheduledShowsController;
import Shodrone.exceptions.FailedRequestException;
import Shodrone.exceptions.UserCancelledException;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduledShowsUI extends AbstractFancyUI {

    private final ScheduledShowsController controller = new ScheduledShowsController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        try {
            if (!authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.CUSTOMERREPRESENTATIVE)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nYou do not have permission to view scheduled shows." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }

            List<String> showDetails = listShowDetails();
            if (showDetails.isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo scheduled shows available." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
            displayShowDetails(showDetails);
            UtilsUI.goBackAndWait();
            return true;
        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            return false;
        } catch (FailedRequestException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed request: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (IOException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNetwork error: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private List<String> listShowDetails() throws FailedRequestException, IOException {
        Iterable<ShowDTO> shows = controller.listShows();
        List<String> showDetails = new ArrayList<>();
        for (ShowDTO show : shows) {
            showDetails.add(String.format("%s - %s", show.proposalNumber, show.showDate));
        }
        return showDetails;
    }

    private void displayShowDetails(List<String> showDetails) {
        System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nScheduled Shows:\n" + UtilsUI.RESET);
        for (int i = 0; i < showDetails.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, showDetails.get(i));
        }
    }

    @Override
    public String headline() {
        return "Scheduled Shows";
    }
}