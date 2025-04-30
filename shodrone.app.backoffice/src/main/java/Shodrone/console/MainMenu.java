package Shodrone.console;

import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import core.Persitence.Application;
import shodrone.authz.MyUserMenu;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // The options will be put here like the following example:
    // EXAMPLE
    private static final int SHOW_MESSAGE = 1;

    // MAIN MENU EXAMPLE
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 4;

    private static final String SEPARATOR_LABEL = "--------------";

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
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Shodrone [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Shodrone [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        /*
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER,
                CafeteriaRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER,
                CafeteriaRoles.KITCHEN_MANAGER)) {
            final Menu kitchenMenu = buildKitchenMenu();
            mainMenu.addSubMenu(TRACEABILITY_OPTION, kitchenMenu);
        }
        if (authz.isAuthenticatedUserAuthorizedTo(CafeteriaRoles.POWER_USER,
                CafeteriaRoles.MENU_MANAGER)) {
            final Menu dishTypeMenu = buildDishMenu();
            mainMenu.addSubMenu(DISH_OPTION, dishTypeMenu);
            final Menu menusMenu = buildMealsMenu();
            mainMenu.addSubMenu(MEALS_OPTION, menusMenu);
            // reporting
            final Menu reportingDishesMenu = buildReportingDishesMenu();
            mainMenu.addSubMenu(REPORTING_DISHES_OPTION, reportingDishesMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

         */

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    // Example of a menu item
    private Menu buildDishMenu() {
        final Menu menu = new Menu("Dishes >");
        /*
        // dish types
        menu.addItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type",
                new RegisterDishTypeAction());
        menu.addItem(DISH_TYPE_LIST_OPTION, "List all Dish Type", new ListDishTypeAction());
        menu.addItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description",
                new ChangeDishTypeAction());
        menu.addItem(DISH_TYPE_CHANGE_PESIMISTIC_LOCK_OPTION, "Change Dish Type description (Pessimistic Lock)",
                new ChangeDishTypePessimisticLockUI()::show);
        menu.addItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type",
                new ActivateDeactivateDishTypeAction());

        // dishes
        menu.addItem(DISH_REGISTER_OPTION, "Register new Dish", new RegisterDishAction());
        menu.addItem(DISH_LIST_OPTION, "List all Dish", new ListDishAction());

        menu.addItem(DISH_REGISTER_DTO_OPTION, "Register new Dish (via DTO)",
                new RegisterDishViaDTOUI()::show);
        menu.addItem(DISH_LIST_DTO_OPTION, "List all Dish (via DTO)", new ListDishViaDTOUI()::show);

        menu.addItem(DISH_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish",
                new ActivateDeactivateDishAction());
        final Menu changeDishMenu = buildChangeDishMenu();
        menu.addSubMenu(DISH_CHANGE_OPTION, changeDishMenu);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

         */

        return menu;
    }



}
