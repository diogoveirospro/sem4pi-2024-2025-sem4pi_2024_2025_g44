package Shodrone.console.Menu;

import Shodrone.console.Drone.Actions.DroneRunnerAction;
import Shodrone.console.Simulator.Actions.TestingAction;
import Shodrone.console.Simulator.ui.SimulateShowUI;
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

    private static final String SIMULATOR_MENU_TITLE = "  _____    _____     __    __     __    __   _____         ____     ________     ____     ______\n" +
            " / ____\\  (_   _)    \\ \\  / /     ) )  ( (  (_   _)       (    )   (___  ___)   / __ \\   (   __ \\\n" +
            "( (___      | |      () \\/ ()    ( (    ) )   | |         / /\\ \\       ) )     / /  \\ \\   ) (__) )\n" +
            " \\___ \\     | |      / _  _ \\     ) )  ( (    | |        ( (__) )     ( (     ( ()  () ) (    __/\n" +
            "     ) )    | |     / / \\/ \\ \\   ( (    ) )   | |   __    )    (       ) )    ( ()  () )  ) \\ \\  _\n" +
            " ___/ /    _| |__  /_/      \\_\\   ) \\__/ (  __| |___) )  /  /\\  \\     ( (      \\ \\__/ /  ( ( \\ \\_))\n" +
            "/____/    /_____( (/          \\)  \\______/  \\________/  /__(  )__\\    /__\\      \\____/    )_) \\__/\n" +
            "\n";

    private static final String DRONE_RUNNER_TITLE = " ______     ______       ____        __      _    _____      ______     __    __      __      _      __      _    _____   ______\n" +
            "(_  __ \\   (   __ \\     / __ \\      /  \\    / )  / ___/     (   __ \\    ) )  ( (     /  \\    / )    /  \\    / )  / ___/  (   __ \\\n" +
            "  ) ) \\ \\   ) (__) )   / /  \\ \\    / /\\ \\  / /  ( (__        ) (__) )  ( (    ) )   / /\\ \\  / /    / /\\ \\  / /  ( (__     ) (__) )\n" +
            " ( (   ) ) (    __/   ( ()  () )   ) ) ) ) ) )   ) __)      (    __/    ) )  ( (    ) ) ) ) ) )    ) ) ) ) ) )   ) __)   (    __/\n" +
            "  ) )  ) )  ) \\ \\  _  ( ()  () )  ( ( ( ( ( (   ( (          ) \\ \\  _  ( (    ) )  ( ( ( ( ( (    ( ( ( ( ( (   ( (       ) \\ \\  _\n" +
            " / /__/ /  ( ( \\ \\_))  \\ \\__/ /   / /  \\ \\/ /    \\ \\___     ( ( \\ \\_))  ) \\__/ (   / /  \\ \\/ /    / /  \\ \\/ /    \\ \\___  ( ( \\ \\_))\n" +
            "(______/    )_) \\__/    \\____/   (_/    \\__/      \\____\\     )_) \\__/   \\______/  (_/    \\__/    (_/    \\__/      \\____\\  )_) \\__/\n" +
            "\n";


    // MAIN MENU
    private static final int MY_USER_MENU = 1;
    private static final int CONFIGURE_DRONE_RUNNER = 2;
    private static final int TESTING_MENU = 3;

    private static final int SIMULATOR = 1;
    private static final int RUNNER = 1;

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

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.DRONETECH, ShodroneRoles.POWER_USER)) {
            final SubMenu droneRunnerMenu = buildDroneRunnerMenu();
            mainMenu.addSubMenu(CONFIGURE_DRONE_RUNNER, droneRunnerMenu);
            final SubMenu simulatorMenu = buildSimulatorManu();
            mainMenu.addSubMenu( TESTING_MENU, simulatorMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction(""));

        return mainMenu;
    }

    private SubMenu buildDroneRunnerMenu() {

        final SubMenu menu = new SubMenu("Drone Runner", DRONE_RUNNER_TITLE);

        menu.addItem(RUNNER, "Configure Drone Runner", new DroneRunnerAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }


    private SubMenu buildSimulatorManu() {
        final SubMenu menu = new SubMenu("Simulator", SIMULATOR_MENU_TITLE);

        menu.addItem(SIMULATOR, "Simulate a Show", new TestingAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }
}
