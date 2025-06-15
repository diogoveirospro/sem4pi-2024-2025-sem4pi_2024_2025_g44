package Shodrone.console.ShowProposal.ui;

import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.console.ShowProposal.printer.ShowRequestPrinter;
import Shodrone.console.ShowProposal.printer.VideoPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.ShowProposal.application.AddVideoToProposalController;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.Video;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class AddVideoToProposalUI extends AbstractFancyUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AddVideoToProposalController controller = new AddVideoToProposalController();
    private final ShowProposalPrinter printer = new ShowProposalPrinter();
    private final VideoPrinter videoPrinter = new VideoPrinter();
    private final ShowRequestPrinter showRequestPrinter = new ShowRequestPrinter();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                ShowRequest showRequest = selectShowRequest();
                ShowProposal showProposal = selectProposal(showRequest);
                if (showProposal == null) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                if (showProposal.video() != null) {
                    boolean confirm = UtilsUI.confirm("This proposal already has a video associated with it. Do you want to change it? (Y/N): ");
                    if (!confirm) {
                        System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nNo changes were made." + UtilsUI.RESET);
                        return false;
                    }
                }
                Video video = selectVideo();
                if (video == null) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                controller.addVideoToProposal(showProposal, video);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nVideo added successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (UserCancelledException e) {
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }


    @Override
    public String headline() {
        return "Add Video to Proposal";
    }

    private ShowProposal selectProposal(ShowRequest showRequest) {
        Iterable<ShowProposal> showProposals = controller.listProposals(showRequest);
        if (showProposals == null || !showProposals.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo show proposals are available for the chosen request." + UtilsUI.RESET);
            return null;
        }
        List<ShowProposal> showProposalList = new ArrayList<>();
        showProposals.forEach(showProposalList::add);
        int option;
        do {
            ListWidget<ShowProposal> showProposalListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "\n\nChoose a Show Proposal\n" + UtilsUI.RESET, showProposalList, printer);
            showProposalListWidget.show();
            option = UtilsUI.selectsIndex(showProposalList);
            if (option == -2) {
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return showProposalList.get(option);
            }
        } while (true);
    }

    private ShowRequest selectShowRequest() {
        Iterable<ShowRequest> showRequests = controller.listRequests();
        if (showRequests == null || !showRequests.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo show requests available." + UtilsUI.RESET);
            return null;
        }
        List<ShowRequest> showRequestList = new ArrayList<>();
        showRequests.forEach(showRequestList::add);
        int option;
        do {
            ListWidget<ShowRequest> showRequestListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "\nChoose a Show Request\n" + UtilsUI.RESET, showRequestList, showRequestPrinter);
            showRequestListWidget.show();
            option = UtilsUI.selectsIndex(showRequestList);
            if (option == -2) {
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return showRequestList.get(option);
            }
        } while (true);
    }

    private Video selectVideo() {
        List<Video> videoList = controller.listVideos();
        if (videoList == null || videoList.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo videos are available." + UtilsUI.RESET);
            return null;
        }
        int option;
        do {
            ListWidget<Video> videoListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "\n\nChoose a Video\n" + UtilsUI.RESET, videoList, videoPrinter);
            videoListWidget.show();
            option = UtilsUI.selectsIndex(videoList);
            if (option == -2) {
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return videoList.get(option);
            }
        } while (true);
    }
}
