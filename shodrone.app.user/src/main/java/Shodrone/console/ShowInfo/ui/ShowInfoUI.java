package Shodrone.console.ShowInfo.ui;

import Shodrone.DTO.ShowDTO;
import Shodrone.console.ShowInfo.controller.ShowInfoController;
import Shodrone.console.ShowInfo.printer.ShowPrinter;
import Shodrone.console.ShowInfo.printer.ShowPrinterSmall;
import Shodrone.exceptions.UserCancelledException;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;


public class ShowInfoUI extends AbstractFancyUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ShowInfoController controller = new ShowInfoController();
    private final ShowPrinter showPrinter = new ShowPrinter();
    private final ShowPrinterSmall showPrinterSmall = new ShowPrinterSmall();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.CUSTOMERREPRESENTATIVE)) {
                int selectedShow = selectShow();
                if (selectedShow == -1) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                ShowDTO show = controller.obtainShow(selectedShow);
                if (show == null) {
                    throw new IllegalArgumentException("Show not found for the selected option.");
                }
                printSelectedShow(show);
                UtilsUI.goBackAndWait();
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private int selectShow() {
        Iterable<ShowDTO> shows = controller.listShows();
        if (shows == null || !shows.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo shows available." + UtilsUI.RESET);
            return -1;
        }
        List<ShowDTO> showList = new ArrayList<>();
        shows.forEach(showList::add);
        int option;
        do {
            ListWidget<ShowDTO> showListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "Choose a Show\n" + UtilsUI.RESET, showList, showPrinterSmall);
            showListWidget.show();
            option = UtilsUI.selectsIndex(showList);
            if (option == -2) {
                return -1;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return option;
            }
        } while (true);
    }

    private void printSelectedShow(ShowDTO show) {
        if (show == null) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo show selected to display." + UtilsUI.RESET);
            return;
        }
        System.out.println(UtilsUI.BOLD + UtilsUI.BLUE + "\nSelected Show Details:\n" + UtilsUI.RESET);
        showPrinter.visit(show);
    }

    @Override
    public String headline() {
        return "Show Information";
    }
}
