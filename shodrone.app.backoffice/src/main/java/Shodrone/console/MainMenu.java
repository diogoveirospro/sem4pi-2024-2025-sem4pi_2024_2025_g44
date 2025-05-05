package Shodrone.console;

import Shodrone.console.Customer.ui.*;
import Shodrone.console.ShowRequest.ui.EditShowRequestUI;
import Shodrone.console.ShowRequest.ui.ListShowRequestsUI;
import Shodrone.console.ShowRequest.ui.RegisterShowRequestUI;
import Shodrone.console.authz.ui.ActivateDeactivateUserUI;
import Shodrone.console.authz.ui.ListUsersUI;
import Shodrone.console.authz.ui.RegisterUserUI;
import core.User.domain.ShodroneRoles;
import eapli.framework.actions.Actions;
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
import core.Persistence.Application;
import shodrone.authz.MyUserMenu;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    //USERS
    private static final int REGISTER_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int ACTIVATE_DEACTIVATE_USER_OPTION = 3;

    // USER MENU (This has no US associated it is just a good thing to add for UX)
    private static final int CHANGE_USERNAME_OPTION = 1;
    private static final int CHANGE_PASSWORD_OPTION = 2;


    // The options will be put here like the following example:
    // EXAMPLE
    private static final int SHOW_MESSAGE = 1;

    // MAIN MENU
    private static final int MY_USER_MENU = 1;
    private static final int USERS_MENU = 2;
    private static final int CUSTOMER_MENU = 3;
    private static final int FIGURE_MENU = 4;
    private static final int SHOW_REQUEST_MENU = 5;
    private static final int DRONE_MENU = 6;
    private static final int FIGURE_CATEGORY_MENU = 7;
    private static final int HELP_MENU = 8;

    // CUSTOMER MENU
    private static final int REGISTER_CUSTOMER_OPTION = 1;
    private static final int ADD_CUSTOMER_REPRESENTATIVE_OPTION = 2;
    private static final int LIST_CUSTOMER_REPRESENTATIVES_OPTION = 3;
    private static final int EDIT_CUSTOMER_REPRESENTATIVE_OPTION = 4;
    private static final int DISABLE_CUSTOMER_REPRESENTATIVE_OPTION = 5;

    // SHOW REQUEST MENU
    private static final int REGISTER_SHOW_REQUEST_OPTION = 1;
    private static final int LIST_SHOW_REQUESTS_OPTION = 2;
    private static final int EDIT_SHOW_REQUEST_OPTION = 3;

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
        mainMenu.addSubMenu(MY_USER_MENU, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        
        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER,
                ShodroneRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_MENU, usersMenu);

        }
        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER,
                ShodroneRoles.COLLABORATOR)) {
            final Menu customerMenu = buildCustomersMenu();
            mainMenu.addSubMenu(CUSTOMER_MENU, customerMenu);
            final Menu showRequestMenu = buildShowRequestMenu();
            mainMenu.addSubMenu(SHOW_REQUEST_MENU, showRequestMenu);
        }


        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

         

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildShowRequestMenu() {
        final Menu menu = new Menu("Show Requests >");

        menu.addItem(REGISTER_SHOW_REQUEST_OPTION, "Register new Show Request", new RegisterShowRequestUI()::show);
        menu.addItem(LIST_SHOW_REQUESTS_OPTION, "List all Show Requests", new ListShowRequestsUI()::show);
        menu.addItem(EDIT_SHOW_REQUEST_OPTION, "Edit a Show Request", new EditShowRequestUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCustomersMenu() {
        final Menu menu = new Menu("Customers >");

        menu.addItem(REGISTER_CUSTOMER_OPTION, "Register new Customer", new RegisterCustomerUI()::show);
        menu.addItem(ADD_CUSTOMER_REPRESENTATIVE_OPTION, "Add a Representative to a Customer", new AddCustomerRepresentativeUI()::show);
        menu.addItem(LIST_CUSTOMER_REPRESENTATIVES_OPTION, "List the Representatives of a Customer", new ListCustomerRepresentativesUI()::show);
        menu.addItem(EDIT_CUSTOMER_REPRESENTATIVE_OPTION, "Edit a Customer Representative", new EditCustomerRepresentativeUI()::show);
        menu.addItem(DISABLE_CUSTOMER_REPRESENTATIVE_OPTION, "Deactivate a Customer Representative", new DeactivateCustomerRepresentativeUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(REGISTER_USER_OPTION, "Add User", new RegisterUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersUI()::show);
        menu.addItem(ACTIVATE_DEACTIVATE_USER_OPTION, "Deactivate User", new ActivateDeactivateUserUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
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
