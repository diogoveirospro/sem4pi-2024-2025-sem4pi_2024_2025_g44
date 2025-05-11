package Shodrone.console;

import Shodrone.console.Category.actions.AddCategoryUI;
import Shodrone.console.Category.actions.ChangeCategoryStatusUI;
import Shodrone.console.Category.actions.EditCategoryUI;
import Shodrone.console.Category.printer.ListExistingCategoriesUI;
import Shodrone.console.Customer.ui.*;
import Shodrone.console.Drone.AddDroneUI;
import Shodrone.console.Drone.ListDroneUI;
import Shodrone.console.Drone.RemoveDroneUI;
import Shodrone.console.Figure.actions.AddFigureToCatalogueUI;
import Shodrone.console.Figure.actions.DecommissionFigureUI;
import Shodrone.console.Figure.actions.SearchCatalogueUI;
import Shodrone.console.Figure.printer.ListPublicCatalogueUI;
import Shodrone.console.ShowRequest.ui.EditShowRequestUI;
import Shodrone.console.ShowRequest.ui.ListShowRequestsUI;
import Shodrone.console.ShowRequest.ui.RegisterShowRequestUI;
import Shodrone.console.authz.ui.DisableEnableUserUI;
import Shodrone.console.authz.ui.ListUsersUI;
import Shodrone.console.authz.ui.RegisterUserUI;
import core.User.domain.ShodroneRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import core.Persistence.Application;
import shodrone.authz.MyUserMenu;
import shodrone.presentation.AbstractFancyUI;

/**
 * TODO split this class in more specialized classes for each menu
 *
 */
public class MainMenu extends AbstractFancyUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int REGISTER_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int ACTIVATE_DEACTIVATE_USER_OPTION = 3;

    // USER MENU (This has no US associated it is just a good thing to add for UX)
    private static final int CHANGE_USERNAME_OPTION = 1;
    private static final int CHANGE_PASSWORD_OPTION = 2;

    // MAIN MENU
    private static final int MY_USER_MENU = 1;
    private static final int HELP_MENU = 8;

    // ADMIN MENUS
    private static final int ADMIN_USERS_MENU = 2;

    // CRM COLLABORATOR MENUS
    private static final int COLLABORATOR_CUSTOMER_MENU = 2;
    private static final int COLLABORATOR_FIGURE_MENU = 3;
    private static final int COLLABORATOR_SHOW_REQUEST_MENU = 4;

    // SHOW DESIGNER MENUS
    private static final int SHOW_DESIGNER_FIGURE_MENU = 2;
    private static final int FIGURE_CATEGORY_MENU = 3;

    // CRM MANAGER MENUS
    private static final int CRM_MANAGER_FIGURE_MENU = 2;

    // DRONE TECH MENUS
    private static final int DRONE_MENU = 2;

    // POWER USER MENUS
    private static final int POWER_USER_USERS_MENU = 2;
    private static final int POWER_USER_CUSTOMER_MENU = 3;
    private static final int POWER_USER_FIGURE_MENU = 4;
    private static final int POWER_USER_SHOW_REQUEST_MENU = 5;
    private static final int POWER_USER_FIGURE_CATEGORY_MENU = 6;
    private static final int POWER_USER_DRONE_MENU = 7;

    // CUSTOMER MENU
    private static final int REGISTER_CUSTOMER_OPTION = 1;
    private static final int ADD_CUSTOMER_REPRESENTATIVE_OPTION = 2;
    private static final int LIST_CUSTOMER_REPRESENTATIVES_OPTION = 3;
    private static final int EDIT_CUSTOMER_REPRESENTATIVE_OPTION = 4;
    private static final int DISABLE_CUSTOMER_REPRESENTATIVE_OPTION = 5;

    // FIGURE CRM COLLABORATOR MENU
    private static final int LIST_FIGURE_PUBLIC_CATALOGUE_OPTION = 1;
    private static final int SEARCH_FIGURE_CATALOGUE_OPTION = 2;

    // FIGURE SHOW DESIGNER MENU
    private static final int ADD_FIGURE_CATALOGUE_OPTION = 1;

    // FIGURE CRM MANAGER MENU
    private static final int DECOMMISSION_FIGURE_OPTION = 1;

    // FIGURE POWER USER MENU
    private static final int PU_LIST_FIGURE_PUBLIC_CATALOGUE_OPTION = 1;
    private static final int PU_SEARCH_FIGURE_CATALOGUE_OPTION = 2;
    private static final int PU_ADD_FIGURE_CATALOGUE_OPTION = 3;
    private static final int PU_DECOMMISSION_FIGURE_OPTION = 4;

    // SHOW REQUEST MENU
    private static final int REGISTER_SHOW_REQUEST_OPTION = 1;
    private static final int LIST_SHOW_REQUESTS_OPTION = 2;
    private static final int EDIT_SHOW_REQUEST_OPTION = 3;

    // FIGURE CATEGORY MENU
    private static final int ADD_CATEGORY_OPTION = 1;
    private static final int EDIT_CATEGORY_OPTION = 2;
    private static final int LIST_CATEGORIES_OPTION = 3;
    private static final int ACTIVATE_DEACTIVATE_CATEGORY_OPTION = 4;

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

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(ADMIN_USERS_MENU, usersMenu);

        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.COLLABORATOR)) {
            final Menu customerMenu = buildCustomersMenu();
            mainMenu.addSubMenu(COLLABORATOR_CUSTOMER_MENU, customerMenu);

            final Menu figureMenu = buildCollaboratorFiguresMenu();
            mainMenu.addSubMenu(COLLABORATOR_FIGURE_MENU, figureMenu);

            final Menu showRequestMenu = buildShowRequestMenu();
            mainMenu.addSubMenu(COLLABORATOR_SHOW_REQUEST_MENU, showRequestMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.SHOWDESIGNER)) {
            final Menu showDesignerFiguresMenu = buildShowDesignerFiguresMenu();
            mainMenu.addSubMenu(SHOW_DESIGNER_FIGURE_MENU, showDesignerFiguresMenu);

            final Menu figureCategoryMenu = buildCategoriesMenu();
            mainMenu.addSubMenu(FIGURE_CATEGORY_MENU, figureCategoryMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.MANAGER)) {
            final Menu managerFiguresMenu = buildManagerFiguresMenu();
            mainMenu.addSubMenu(CRM_MANAGER_FIGURE_MENU, managerFiguresMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.DRONETECH)) {
            final Menu dronesMenu = buildDronesMenu();
            mainMenu.addSubMenu(DRONE_MENU, dronesMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER)){
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(POWER_USER_USERS_MENU, usersMenu);

            final Menu customerMenu = buildCustomersMenu();
            mainMenu.addSubMenu(POWER_USER_CUSTOMER_MENU, customerMenu);

            final Menu figureMenu = buildFiguresMenu();
            mainMenu.addSubMenu(POWER_USER_FIGURE_MENU, figureMenu);

            final Menu showRequestMenu = buildShowRequestMenu();
            mainMenu.addSubMenu(POWER_USER_SHOW_REQUEST_MENU, showRequestMenu);

            final Menu figureCategoryMenu = buildCategoriesMenu();
            mainMenu.addSubMenu(POWER_USER_FIGURE_CATEGORY_MENU, figureCategoryMenu);

            final Menu dronesMenu = buildDronesMenu();
            mainMenu.addSubMenu(POWER_USER_DRONE_MENU, dronesMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction(""));

        return mainMenu;
    }

    private Menu buildFiguresMenu(){
        final Menu menu = new Menu("Figures");

        menu.addItem(PU_LIST_FIGURE_PUBLIC_CATALOGUE_OPTION, "List Public Catalogue", new ListPublicCatalogueUI()::show);
        menu.addItem(PU_SEARCH_FIGURE_CATALOGUE_OPTION, "Search Figures in the Catalogue", new SearchCatalogueUI()::show);
        menu.addItem(PU_ADD_FIGURE_CATALOGUE_OPTION, "Add Figure to the Catalogue", new AddFigureToCatalogueUI()::show);
        menu.addItem(PU_DECOMMISSION_FIGURE_OPTION, "Decommission Figure", new DecommissionFigureUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCollaboratorFiguresMenu() {
        final Menu menu = new Menu("Figures");

        menu.addItem(LIST_FIGURE_PUBLIC_CATALOGUE_OPTION, "List Public Catalogue", new ListPublicCatalogueUI()::show);
        menu.addItem(SEARCH_FIGURE_CATALOGUE_OPTION, "Search Figures in the Catalogue", new SearchCatalogueUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildShowDesignerFiguresMenu() {
        final Menu menu = new Menu("Figures");

        menu.addItem(ADD_FIGURE_CATALOGUE_OPTION, "Add Figure to the Catalogue", new AddFigureToCatalogueUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildManagerFiguresMenu() {
        final Menu menu = new Menu("Figures");

        menu.addItem(DECOMMISSION_FIGURE_OPTION, "Decommission Figure", new DecommissionFigureUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildShowRequestMenu() {
        final Menu menu = new Menu("Show Requests");

        menu.addItem(REGISTER_SHOW_REQUEST_OPTION, "Register new Show Request", new RegisterShowRequestUI()::show);
        menu.addItem(LIST_SHOW_REQUESTS_OPTION, "List all Show Requests", new ListShowRequestsUI()::show);
        menu.addItem(EDIT_SHOW_REQUEST_OPTION, "Edit a Show Request", new EditShowRequestUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCustomersMenu() {
        final Menu menu = new Menu("Customers");

        menu.addItem(REGISTER_CUSTOMER_OPTION, "Register new Customer", new RegisterCustomerUI()::show);
        menu.addItem(ADD_CUSTOMER_REPRESENTATIVE_OPTION, "Add a Representative to a Customer", new AddCustomerRepresentativeUI()::show);
        menu.addItem(LIST_CUSTOMER_REPRESENTATIVES_OPTION, "List the Representatives of a Customer", new ListCustomerRepresentativesUI()::show);
        menu.addItem(EDIT_CUSTOMER_REPRESENTATIVE_OPTION, "Edit a Customer Representative", new EditCustomerRepresentativeUI()::show);
        menu.addItem(DISABLE_CUSTOMER_REPRESENTATIVE_OPTION, "Deactivate a Customer Representative", new DeactivateCustomerRepresentativeUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users");

        menu.addItem(REGISTER_USER_OPTION, "Register User", new RegisterUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersUI()::show);
        menu.addItem(ACTIVATE_DEACTIVATE_USER_OPTION, "Deactivate User", new DisableEnableUserUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCategoriesMenu() {
        final Menu menu = new Menu("Categories");

        menu.addItem(ADD_CATEGORY_OPTION, "Add Category", new AddCategoryUI()::show);
        menu.addItem(EDIT_CATEGORY_OPTION, "List all Categories", new ListExistingCategoriesUI()::show);
        menu.addItem(LIST_CATEGORIES_OPTION, "Edit Category", new EditCategoryUI()::show);
        menu.addItem(ACTIVATE_DEACTIVATE_CATEGORY_OPTION, "Activate/Deactivate Category", new ChangeCategoryStatusUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildDronesMenu() {
        final Menu menu = new Menu("Drones");

        menu.addItem(LIST_USERS_OPTION, "Add a drone from inventory", new AddDroneUI()::show);
            menu.addItem(ACTIVATE_DEACTIVATE_USER_OPTION, "Remove a drone from inventory", new RemoveDroneUI()::show);
        menu.addItem(ACTIVATE_DEACTIVATE_USER_OPTION, "List a type of drone", new ListDroneUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }
}
