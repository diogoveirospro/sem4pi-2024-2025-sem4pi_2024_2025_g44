package Shodrone.console.Menu;

import Shodrone.console.SendFeedbackProposal.actions.SendFeedbackProposalAction;
import Shodrone.console.AnalyseProposal.actions.AnalyseProposalAction;
import Shodrone.console.SheduledShows.actions.ScheduledShowsAction;
import Shodrone.console.ShowInfo.actions.ShowInfoAction;
import core.Persistence.Application;
import core.User.domain.ShodroneRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

/**
 * TODO split this class in more specialized classes for each menu
 */
public class MainMenu extends AbstractFancyUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // BIG TITLES SUBMENUS

    private static final String MY_PROPOSALS_MENU_TITLE = "   __    __    __      __     _____    ______       ____     _____      ____      _____     ____     _____        _____\n" +
            "   \\ \\  / /    ) \\    / (    (  __ \\  (   __ \\     / __ \\   (  __ \\    / __ \\    / ____\\   (    )   (_   _)      / ____\\\n" +
            "   () \\/ ()     \\ \\  / /      ) )_) )  ) (__) )   / /  \\ \\   ) )_) )  / /  \\ \\  ( (___     / /\\ \\     | |       ( (___\n" +
            "   / _  _ \\      \\ \\/ /      (  ___/  (    __/   ( ()  () ) (  ___/  ( ()  () )  \\___ \\   ( (__) )    | |        \\___ \\\n" +
            "  / / \\/ \\ \\      \\  /        ) )      ) \\ \\  _  ( ()  () )  ) )     ( ()  () )      ) )   )    (     | |   __       ) )\n" +
            " /_/      \\_\\      )(        ( (      ( ( \\ \\_))  \\ \\__/ /  ( (       \\ \\__/ /   ___/ /   /  /\\  \\  __| |___) )  ___/ /\n" +
            "(/          \\)    /__\\       /__\\      )_) \\__/    \\____/   /__\\       \\____/   /____/   /__(  )__\\ \\________/  /____/\n" +
            "\n";


    // MAIN MENU
    private static final int MY_USER_MENU = 1;
    private static final int MY_PROPOSALS_MENU = 2;

    // MY PROPOSALS MENU
    private static final int ANALYSE_PROPOSAL = 1;
    private static final int SEND_FEEDBACK_PROPOSAL = 2;
    private static final int SCHEDULED_SHOWS = 3;
    private static final int SHOW_INFO = 4;


    private static final String SEPARATOR_LABEL = "----------------------------";
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRendererShodrone renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRendererShodrone(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRendererShodrone(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {
        UtilsUI.clearConsole();
        return authz.session()
                .map(session -> {
                    final String username = session.authenticatedUser().identity().toString();
                    return String.format("SHODRONE // Logged in as @%s", username.toUpperCase());
                })
                .orElse("SHODRONE // == ANONYMOUS USER ==");
    }


    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_MENU, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.CUSTOMERREPRESENTATIVE)) {
            final SubMenu customerMenu = buildMyProposalsMenu();
            mainMenu.addSubMenu(MY_PROPOSALS_MENU, customerMenu);
        } else {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD +
                    "You are not authorised to log in to the User App. Please contact your System Administrator.\n" + UtilsUI.RESET);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction(""));

        return mainMenu;
    }

    private SubMenu buildMyProposalsMenu() {
        final SubMenu menu = new SubMenu("My Proposals", MY_PROPOSALS_MENU_TITLE);

        menu.addItem(ANALYSE_PROPOSAL, "Analyse Proposal", new AnalyseProposalAction());
        menu.addItem(SEND_FEEDBACK_PROPOSAL, "Send Feedback Proposal", new SendFeedbackProposalAction());
        menu.addItem(SCHEDULED_SHOWS, "Scheduled Shows", new ScheduledShowsAction());
        menu.addItem(SHOW_INFO, "Show Info", new ShowInfoAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }


}
