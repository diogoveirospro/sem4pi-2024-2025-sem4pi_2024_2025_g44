package Shodrone.console.Menu;

import Shodrone.console.SendFeedbackProposal.actions.SendFeedbackProposalAction;
import Shodrone.console.AnalyseProposal.actions.AnalyseProposalAction;
import Shodrone.console.SheduledShows.actions.ScheduledShowsAction;
import Shodrone.console.ShowInfo.actions.ShowInfoAction;
import core.Persistence.Application;
import core.User.domain.ShodroneRoles;
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

    private static final String SHOW_PROPOSALS_MENU_TITLE = "  _____   __    __     ____     ___       ___      _____    ______       ____     _____      ____      _____     ____     _____        _____\n" +
            " / ____\\ (  \\  /  )   / __ \\   (  (       )  )    (  __ \\  (   __ \\     / __ \\   (  __ \\    / __ \\    / ____\\   (    )   (_   _)      / ____\\\n" +
            "( (___    \\ (__) /   / /  \\ \\   \\  \\  _  /  /      ) )_) )  ) (__) )   / /  \\ \\   ) )_) )  / /  \\ \\  ( (___     / /\\ \\     | |       ( (___\n" +
            " \\___ \\    ) __ (   ( ()  () )   \\  \\/ \\/  /      (  ___/  (    __/   ( ()  () ) (  ___/  ( ()  () )  \\___ \\   ( (__) )    | |        \\___ \\\n" +
            "     ) )  ( (  ) )  ( ()  () )    )   _   (        ) )      ) \\ \\  _  ( ()  () )  ) )     ( ()  () )      ) )   )    (     | |   __       ) )\n" +
            " ___/ /    ) )( (    \\ \\__/ /     \\  ( )  /       ( (      ( ( \\ \\_))  \\ \\__/ /  ( (       \\ \\__/ /   ___/ /   /  /\\  \\  __| |___) )  ___/ /\n" +
            "/____/    /_/  \\_\\    \\____/       \\_/ \\_/        /__\\      )_) \\__/    \\____/   /__\\       \\____/   /____/   /__(  )__\\ \\________/  /____/\n" +
            "\n";

    private static final String SHOW_MENU_TITLE = "  _____   __    __     ____     ___       ___      _____    ______       ____     _____      ____      _____     ____     _____        _____\n" +
            " / ____\\ (  \\  /  )   / __ \\   (  (       )  )    (  __ \\  (   __ \\     / __ \\   (  __ \\    / __ \\    / ____\\   (    )   (_   _)      / ____\\\n" +
            "( (___    \\ (__) /   / /  \\ \\   \\  \\  _  /  /      ) )_) )  ) (__) )   / /  \\ \\   ) )_) )  / /  \\ \\  ( (___     / /\\ \\     | |       ( (___\n" +
            " \\___ \\    ) __ (   ( ()  () )   \\  \\/ \\/  /      (  ___/  (    __/   ( ()  () ) (  ___/  ( ()  () )  \\___ \\   ( (__) )    | |        \\___ \\\n" +
            "     ) )  ( (  ) )  ( ()  () )    )   _   (        ) )      ) \\ \\  _  ( ()  () )  ) )     ( ()  () )      ) )   )    (     | |   __       ) )\n" +
            " ___/ /    ) )( (    \\ \\__/ /     \\  ( )  /       ( (      ( ( \\ \\_))  \\ \\__/ /  ( (       \\ \\__/ /   ___/ /   /  /\\  \\  __| |___) )  ___/ /\n" +
            "/____/    /_/  \\_\\    \\____/       \\_/ \\_/        /__\\      )_) \\__/    \\____/   /__\\       \\____/   /____/   /__(  )__\\ \\________/  /____/\n" +
            "\n";


    // MAIN MENU
    private static final int MY_USER_MENU = 1;
    private static final int ANALYSE_PROPOSAL = 2;
    private static final int SEND_FEEDBACK_PROPOSAL = 3;
    private static final int SCHEDULED_SHOWS = 4;
    private static final int SHOW_INFO = 5;


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
            mainMenu.addItem(ANALYSE_PROPOSAL, "Analyse Proposal", new AnalyseProposalAction());
            mainMenu.addItem(SEND_FEEDBACK_PROPOSAL, "Send Feedback Proposal", new SendFeedbackProposalAction());
            mainMenu.addItem(SCHEDULED_SHOWS, "Scheduled Shows", new ScheduledShowsAction());
            mainMenu.addItem(SHOW_INFO, "Show Info", new ShowInfoAction());
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction(""));

        return mainMenu;
    }


}
