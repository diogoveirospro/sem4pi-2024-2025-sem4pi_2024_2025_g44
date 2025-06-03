package Shodrone.console.Menu;

import Shodrone.console.Category.actions.AddCategoryUI;
import Shodrone.console.Category.actions.ChangeCategoryStatusUI;
import Shodrone.console.Category.actions.EditCategoryUI;
import Shodrone.console.Category.printer.ListExistingCategoriesUI;
import Shodrone.console.Customer.ui.*;
import Shodrone.console.Drone.ui.AddDroneUI;
import Shodrone.console.Model.ui.CreateModelUI;
import Shodrone.console.Drone.ui.ListDroneUI;
import Shodrone.console.Drone.ui.RemoveDroneUI;
import Shodrone.console.Figure.actions.AddFigureToCatalogueUI;
import Shodrone.console.Figure.actions.DecommissionFigureUI;
import Shodrone.console.Figure.actions.SearchCatalogueUI;
import Shodrone.console.Figure.actions.ListPublicCatalogueUI;
import Shodrone.console.ShowProposal.ui.AddVideoToProposalUI;
import Shodrone.console.ShowProposal.ui.ConfigShowPropUI;
import Shodrone.console.ShowProposal.ui.ConfigureProposalDocumentUI;
import Shodrone.console.ShowProposal.ui.CreateShowProposalUI;
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
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import core.Persistence.Application;
import shodrone.authz.ui.LoginUI;
import shodrone.infrastructure.authz.AuthenticationCredentialHandler;
import shodrone.infrastructure.authz.CredentialHandler;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

/**
 * TODO split this class in more specialized classes for each menu
 */
public class MainMenu extends AbstractFancyUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // BIG TITLES SUBMENUS
    private static final String USERS_MENU_TITLE = " __    __    _____    _____   ______      _____\n" +
            " ) )  ( (   / ____\\  / ___/  (   __ \\    / ____\\\n" +
            "( (    ) ) ( (___   ( (__     ) (__) )  ( (___\n" +
            " ) )  ( (   \\___ \\   ) __)   (    __/    \\___ \\\n" +
            "( (    ) )      ) ) ( (       ) \\ \\  _       ) )\n" +
            " ) \\__/ (   ___/ /   \\ \\___  ( ( \\ \\_))  ___/ /\n" +
            " \\______/  /____/     \\____\\  )_) \\__/  /____/\n" +
            "\n";
    private static final String CUSTOMERS_MENU_TITLE = "   ____   __    __    _____   ________     ____       __    __      _____   ______      _____\n" +
            "  / ___)  ) )  ( (   / ____\\ (___  ___)   / __ \\      \\ \\  / /     / ___/  (   __ \\    / ____\\\n" +
            " / /     ( (    ) ) ( (___       ) )     / /  \\ \\     () \\/ ()    ( (__     ) (__) )  ( (___\n" +
            "( (       ) )  ( (   \\___ \\     ( (     ( ()  () )    / _  _ \\     ) __)   (    __/    \\___ \\\n" +
            "( (      ( (    ) )      ) )     ) )    ( ()  () )   / / \\/ \\ \\   ( (       ) \\ \\  _       ) )\n" +
            " \\ \\___   ) \\__/ (   ___/ /     ( (      \\ \\__/ /   /_/      \\_\\   \\ \\___  ( ( \\ \\_))  ___/ /\n" +
            "  \\____)  \\______/  /____/      /__\\      \\____/   (/          \\)   \\____\\  )_) \\__/  /____/\n" +
            "\n";
    private static final String FIGURES_MENU_TITLE = " _________    _____      _____    __    __   ______      _____    _____\n" +
            "(_   _____)  (_   _)    / ___ \\   ) )  ( (  (   __ \\    / ___/   / ____\\\n" +
            "  ) (___       | |     / /   \\_) ( (    ) )  ) (__) )  ( (__    ( (___\n" +
            " (   ___)      | |    ( (  ____   ) )  ( (  (    __/    ) __)    \\___ \\\n" +
            "  ) (          | |    ( ( (__  ) ( (    ) )  ) \\ \\  _  ( (           ) )\n" +
            " (   )        _| |__   \\ \\__/ /   ) \\__/ (  ( ( \\ \\_))  \\ \\___   ___/ /\n" +
            "  \\_/        /_____(    \\____/    \\______/   )_) \\__/    \\____\\ /____/\n" +
            "\n";
    private static final String SHOW_REQUEST_MENU_TITLE = "  _____   __    __     ____     ___       ___      ______      _____     ____      __    __    _____    _____   ________    _____\n" +
            " / ____\\ (  \\  /  )   / __ \\   (  (       )  )    (   __ \\    / ___/    / __ \\     ) )  ( (   / ___/   / ____\\ (___  ___)  / ____\\\n" +
            "( (___    \\ (__) /   / /  \\ \\   \\  \\  _  /  /      ) (__) )  ( (__     / /  \\ \\   ( (    ) ) ( (__    ( (___       ) )    ( (___\n" +
            " \\___ \\    ) __ (   ( ()  () )   \\  \\/ \\/  /      (    __/    ) __)   ( (    ) )   ) )  ( (   ) __)    \\___ \\     ( (      \\___ \\\n" +
            "     ) )  ( (  ) )  ( ()  () )    )   _   (        ) \\ \\  _  ( (      ( (  /\\) )  ( (    ) ) ( (           ) )     ) )         ) )\n" +
            " ___/ /    ) )( (    \\ \\__/ /     \\  ( )  /       ( ( \\ \\_))  \\ \\___   \\ \\_\\ \\/    ) \\__/ (   \\ \\___   ___/ /     ( (      ___/ /\n" +
            "/____/    /_/  \\_\\    \\____/       \\_/ \\_/         )_) \\__/    \\____\\   \\___\\ \\_   \\______/    \\____\\ /____/      /__\\    /____/\n" +
            "                                                                             \\__)\n";
    private static final String CATEGORIES_MENU_TITLE = "   ____     ____     ________    _____      _____      ____     ______      _____    _____    _____\n" +
            "  / ___)   (    )   (___  ___)  / ___/     / ___ \\    / __ \\   (   __ \\    (_   _)  / ___/   / ____\\\n" +
            " / /       / /\\ \\       ) )    ( (__      / /   \\_)  / /  \\ \\   ) (__) )     | |   ( (__    ( (___\n" +
            "( (       ( (__) )     ( (      ) __)    ( (  ____  ( ()  () ) (    __/      | |    ) __)    \\___ \\\n" +
            "( (        )    (       ) )    ( (       ( ( (__  ) ( ()  () )  ) \\ \\  _     | |   ( (           ) )\n" +
            " \\ \\___   /  /\\  \\     ( (      \\ \\___    \\ \\__/ /   \\ \\__/ /  ( ( \\ \\_))   _| |__  \\ \\___   ___/ /\n" +
            "  \\____) /__(  )__\\    /__\\      \\____\\    \\____/     \\____/    )_) \\__/   /_____(   \\____\\ /____/\n" +
            "\n";
    private static final String DRONES_MENU_TITLE = " ______     ______       ____        __      _    _____    _____\n" +
            "(_  __ \\   (   __ \\     / __ \\      /  \\    / )  / ___/   / ____\\\n" +
            "  ) ) \\ \\   ) (__) )   / /  \\ \\    / /\\ \\  / /  ( (__    ( (___\n" +
            " ( (   ) ) (    __/   ( ()  () )   ) ) ) ) ) )   ) __)    \\___ \\\n" +
            "  ) )  ) )  ) \\ \\  _  ( ()  () )  ( ( ( ( ( (   ( (           ) )\n" +
            " / /__/ /  ( ( \\ \\_))  \\ \\__/ /   / /  \\ \\/ /    \\ \\___   ___/ /\n" +
            "(______/    )_) \\__/    \\____/   (_/    \\__/      \\____\\ /____/\n" +
            "\n";

    private static final String SHOW_PROPOSALS_MENU_TITLE = "  _____   __    __     ____     ___       ___      _____    ______       ____     _____      ____      _____     ____     _____        _____\n" +
            " / ____\\ (  \\  /  )   / __ \\   (  (       )  )    (  __ \\  (   __ \\     / __ \\   (  __ \\    / __ \\    / ____\\   (    )   (_   _)      / ____\\\n" +
            "( (___    \\ (__) /   / /  \\ \\   \\  \\  _  /  /      ) )_) )  ) (__) )   / /  \\ \\   ) )_) )  / /  \\ \\  ( (___     / /\\ \\     | |       ( (___\n" +
            " \\___ \\    ) __ (   ( ()  () )   \\  \\/ \\/  /      (  ___/  (    __/   ( ()  () ) (  ___/  ( ()  () )  \\___ \\   ( (__) )    | |        \\___ \\\n" +
            "     ) )  ( (  ) )  ( ()  () )    )   _   (        ) )      ) \\ \\  _  ( ()  () )  ) )     ( ()  () )      ) )   )    (     | |   __       ) )\n" +
            " ___/ /    ) )( (    \\ \\__/ /     \\  ( )  /       ( (      ( ( \\ \\_))  \\ \\__/ /  ( (       \\ \\__/ /   ___/ /   /  /\\  \\  __| |___) )  ___/ /\n" +
            "/____/    /_/  \\_\\    \\____/       \\_/ \\_/        /__\\      )_) \\__/    \\____/   /__\\       \\____/   /____/   /__(  )__\\ \\________/  /____/\n" +
            "\n";

    // USERS
    private static final int REGISTER_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int ACTIVATE_DEACTIVATE_USER_OPTION = 3;

    // MAIN MENU
    private static final int MY_USER_MENU = 1;
    private static final int HELP_MENU = 8;

    // ADMIN MENUS
    private static final int ADMIN_USERS_MENU = 2;

    // CRM COLLABORATOR MENUS
    private static final int COLLABORATOR_CUSTOMER_MENU = 2;
    private static final int COLLABORATOR_FIGURE_MENU = 3;
    private static final int COLLABORATOR_SHOW_PROPOSALS_MENU = 4;
    private static final int COLLABORATOR_SHOW_REQUEST_MENU = 5;

    // SHOW DESIGNER MENUS
    private static final int SHOW_DESIGNER_FIGURE_MENU = 2;
    private static final int FIGURE_CATEGORY_MENU = 3;

    // CRM MANAGER MENUS
    private static final int CRM_MANAGER_FIGURE_MENU = 2;
    private static final int CRM_MANAGER_SHOW_PROPOSAL_MENU = 3;

    // DRONE TECH MENUS
    private static final int DRONE_MENU = 2;
    private static final int CREATE_MODEL_OPTION = 1;

    private static final int ADD_DRONE_OPTION = 2;
    private static final int REMOVE_DRONE_OPTION = 3;
    private static final int LIST_DRONES_OPTION = 4;

    // POWER USER MENUS
    private static final int POWER_USER_USERS_MENU = 2;
    private static final int POWER_USER_CUSTOMER_MENU = 3;
    private static final int POWER_USER_FIGURE_MENU = 4;
    private static final int POWER_USER_SHOW_REQUEST_MENU = 5;
    private static final int POWER_USER_SHOW_PROPOSAL_MENU = 6;
    private static final int POWER_USER_FIGURE_CATEGORY_MENU = 7;
    private static final int POWER_USER_DRONE_MENU = 8;

    // CUSTOMER MENU
    private static final int REGISTER_CUSTOMER_OPTION = 1;
    private static final int ADD_CUSTOMER_REPRESENTATIVE_OPTION = 2;
    private static final int LIST_CUSTOMER_REPRESENTATIVES_OPTION = 3;
    private static final int EDIT_CUSTOMER_REPRESENTATIVE_OPTION = 4;
    private static final int DISABLE_CUSTOMER_REPRESENTATIVE_OPTION = 5;

    // SHOW PROPOSAL MENU
    private static final int CREATE_SHOW_PROPOSAL_OPTION = 1;
    private static final int ADD_DRONES_TO_SHOW_PROPOSAL_OPTION = 2;
    private static final int ADD_FIGURES_TO_SHOW_PROPOSAL_OPTION = 3;
    private static final int ADD_VIDEO_TO_SHOW_PROPOSAL_OPTION = 4;
    private static final int CONFIGURE_TEMPLATE_OF_SHOW_PROPOSAL_OPTION = 5;
    private static final int SEND_SHOW_PROPOSAL_TO_CUSTOMER_OPTION = 6;
    private static final int MARK_SHOW_PROPOSAL_AS_ACCEPTED_OPTION = 7;

    // FIGURE CRM COLLABORATOR MENU
    private static final int LIST_FIGURE_PUBLIC_CATALOGUE_OPTION = 1;
    private static final int SEARCH_FIGURE_CATALOGUE_OPTION = 2;

    // FIGURE SHOW DESIGNER MENU
    private static final int ADD_FIGURE_CATALOGUE_OPTION = 1;

    // FIGURE CRM MANAGER MENU
    private static final int DECOMMISSION_FIGURE_OPTION = 1;

    // FIGURE POWER USER MENU
    private static final int PU_ADD_FIGURE_CATALOGUE_OPTION = 1;
    private static final int PU_LIST_FIGURE_PUBLIC_CATALOGUE_OPTION = 2;
    private static final int PU_SEARCH_FIGURE_CATALOGUE_OPTION = 3;
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

    // PROPOSAL COLLABORATOR MENU
    private static final int COLLABORATOR_CREATE_SHOW_PROPOSAL_OPTION = 1;
    private static final int COLLABORATOR_ADD_DRONES_TO_SHOW_PROPOSAL_OPTION = 2;
    private static final int COLLABORATOR_ADD_FIGURES_TO_SHOW_PROPOSAL_OPTION = 3;
    private static final int COLLABORATOR_ADD_VIDEO_TO_SHOW_PROPOSAL_OPTION = 4;
    private static final int COLLABORATOR_SEND_SHOW_PROPOSAL_TO_CUSTOMER_OPTION = 5;
    private static final int COLLABORATOR_MARK_SHOW_PROPOSAL_AS_ACCEPTED_OPTION = 6;

    // PROPOSAL MANAGER MENU
    private static final int MANAGER_CONFIGURE_TEMPLATE_OF_SHOW_PROPOSAL_OPTION = 1;

    private static final String SEPARATOR_LABEL = "----------------------------";
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CredentialHandler credentialHandler = new AuthenticationCredentialHandler();

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

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.ADMIN)) {
            final SubMenu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(ADMIN_USERS_MENU, usersMenu);

        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.COLLABORATOR)) {
            final SubMenu customerMenu = buildCustomersMenu();
            mainMenu.addSubMenu(COLLABORATOR_CUSTOMER_MENU, customerMenu);

            final SubMenu figureMenu = buildCollaboratorFiguresMenu();
            mainMenu.addSubMenu(COLLABORATOR_FIGURE_MENU, figureMenu);

            final SubMenu showProposalsMenu = buildCollaboratorShowProposalsMenu();
            mainMenu.addSubMenu(COLLABORATOR_SHOW_PROPOSALS_MENU, showProposalsMenu);

            final SubMenu showRequestMenu = buildShowRequestMenu();
            mainMenu.addSubMenu(COLLABORATOR_SHOW_REQUEST_MENU, showRequestMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.SHOWDESIGNER)) {
            final SubMenu showDesignerFiguresMenu = buildShowDesignerFiguresMenu();
            mainMenu.addSubMenu(SHOW_DESIGNER_FIGURE_MENU, showDesignerFiguresMenu);

            final SubMenu figureCategoryMenu = buildCategoriesMenu();
            mainMenu.addSubMenu(FIGURE_CATEGORY_MENU, figureCategoryMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.MANAGER)) {
            final SubMenu managerFiguresMenu = buildManagerFiguresMenu();
            mainMenu.addSubMenu(CRM_MANAGER_FIGURE_MENU, managerFiguresMenu);

            final SubMenu showProposalsMenu = buildShowProposalsManagerMenu();
            mainMenu.addSubMenu(CRM_MANAGER_SHOW_PROPOSAL_MENU, showProposalsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.DRONETECH)) {
            final SubMenu dronesMenu = buildDronesMenu();
            mainMenu.addSubMenu(DRONE_MENU, dronesMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER)) {
            final SubMenu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(POWER_USER_USERS_MENU, usersMenu);

            final SubMenu customerMenu = buildCustomersMenu();
            mainMenu.addSubMenu(POWER_USER_CUSTOMER_MENU, customerMenu);

            final SubMenu figureMenu = buildFiguresMenu();
            mainMenu.addSubMenu(POWER_USER_FIGURE_MENU, figureMenu);

            final SubMenu showRequestMenu = buildShowRequestMenu();
            mainMenu.addSubMenu(POWER_USER_SHOW_REQUEST_MENU, showRequestMenu);

            final SubMenu showProposalsMenu = buildShowProposalsMenu();
            mainMenu.addSubMenu(POWER_USER_SHOW_PROPOSAL_MENU, showProposalsMenu);

            final SubMenu figureCategoryMenu = buildCategoriesMenu();
            mainMenu.addSubMenu(POWER_USER_FIGURE_CATEGORY_MENU, figureCategoryMenu);

            final SubMenu dronesMenu = buildDronesMenu();
            mainMenu.addSubMenu(POWER_USER_DRONE_MENU, dronesMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.CUSTOMERREPRESENTATIVE)) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD +
                    "You are not allowed to login as a Customer Representative. Please contact your System Administrator.\n" + UtilsUI.RESET);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction(""));

        return mainMenu;
    }

    private SubMenu buildFiguresMenu() {
        final SubMenu menu = new SubMenu("Figures", FIGURES_MENU_TITLE);

        menu.addItem(PU_ADD_FIGURE_CATALOGUE_OPTION, "Add Figure to the Catalogue", new AddFigureToCatalogueUI()::show);
        menu.addItem(PU_LIST_FIGURE_PUBLIC_CATALOGUE_OPTION, "List Public Catalogue", new ListPublicCatalogueUI()::show);
        menu.addItem(PU_SEARCH_FIGURE_CATALOGUE_OPTION, "Search Figures in the Catalogue", new SearchCatalogueUI()::show);
        menu.addItem(PU_DECOMMISSION_FIGURE_OPTION, "Decommission Figure", new DecommissionFigureUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildCollaboratorFiguresMenu() {
        final SubMenu menu = new SubMenu("Figures", FIGURES_MENU_TITLE);

        menu.addItem(LIST_FIGURE_PUBLIC_CATALOGUE_OPTION, "List Public Catalogue", new ListPublicCatalogueUI()::show);
        menu.addItem(SEARCH_FIGURE_CATALOGUE_OPTION, "Search Figures in the Catalogue", new SearchCatalogueUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildShowDesignerFiguresMenu() {
        final SubMenu menu = new SubMenu("Figures", FIGURES_MENU_TITLE);

        menu.addItem(ADD_FIGURE_CATALOGUE_OPTION, "Add Figure to the Catalogue", new AddFigureToCatalogueUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildManagerFiguresMenu() {
        final SubMenu menu = new SubMenu("Figures", FIGURES_MENU_TITLE);

        menu.addItem(DECOMMISSION_FIGURE_OPTION, "Decommission Figure", new DecommissionFigureUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildShowRequestMenu() {
        final SubMenu menu = new SubMenu("Show Requests", SHOW_REQUEST_MENU_TITLE);

        menu.addItem(REGISTER_SHOW_REQUEST_OPTION, "Register new Show Request", new RegisterShowRequestUI()::show);
        menu.addItem(LIST_SHOW_REQUESTS_OPTION, "List All Show Requests", new ListShowRequestsUI()::show);
        menu.addItem(EDIT_SHOW_REQUEST_OPTION, "Edit a Show Request", new EditShowRequestUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildCustomersMenu() {
        final SubMenu menu = new SubMenu("Customers", CUSTOMERS_MENU_TITLE);

        menu.addItem(REGISTER_CUSTOMER_OPTION, "Register new Customer", new RegisterCustomerUI()::show);
        menu.addItem(ADD_CUSTOMER_REPRESENTATIVE_OPTION, "Add a Representative to a Customer", new AddCustomerRepresentativeUI()::show);
        menu.addItem(LIST_CUSTOMER_REPRESENTATIVES_OPTION, "List the Representatives of a Customer", new ListCustomerRepresentativesUI()::show);
        menu.addItem(EDIT_CUSTOMER_REPRESENTATIVE_OPTION, "Edit a Customer Representative", new EditCustomerRepresentativeUI()::show);
        menu.addItem(DISABLE_CUSTOMER_REPRESENTATIVE_OPTION, "Deactivate a Customer Representative", new DeactivateCustomerRepresentativeUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildUsersMenu() {
        final SubMenu menu = new SubMenu("Users", USERS_MENU_TITLE);

        menu.addItem(REGISTER_USER_OPTION, "Register User", new RegisterUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List All Users", new ListUsersUI()::show);
        menu.addItem(ACTIVATE_DEACTIVATE_USER_OPTION, "Disable and Enable Users", new DisableEnableUserUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildCategoriesMenu() {
        final SubMenu menu = new SubMenu("Categories", CATEGORIES_MENU_TITLE);

        menu.addItem(ADD_CATEGORY_OPTION, "Add Category", new AddCategoryUI()::show);
        menu.addItem(EDIT_CATEGORY_OPTION, "List All Categories", new ListExistingCategoriesUI()::show);
        menu.addItem(LIST_CATEGORIES_OPTION, "Edit Category", new EditCategoryUI()::show);
        menu.addItem(ACTIVATE_DEACTIVATE_CATEGORY_OPTION, "Activate and Deactivate Category", new ChangeCategoryStatusUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildDronesMenu() {
        final SubMenu menu = new SubMenu("Drones", DRONES_MENU_TITLE);

        menu.addItem(CREATE_MODEL_OPTION, "Create a Model", new CreateModelUI()::show);
        menu.addItem(ADD_DRONE_OPTION, "Add a Drone to the Inventory", new AddDroneUI()::show);
        menu.addItem(REMOVE_DRONE_OPTION, "Remove a Drone from Inventory", new RemoveDroneUI()::show);
        menu.addItem(LIST_DRONES_OPTION, "List a Model of Drone", new ListDroneUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildShowProposalsMenu() {
        final SubMenu menu = new SubMenu("Show Proposals", SHOW_PROPOSALS_MENU_TITLE);

        menu.addItem(CREATE_SHOW_PROPOSAL_OPTION, "Create a Show Proposal", new CreateShowProposalUI()::show);
        menu.addItem(ADD_DRONES_TO_SHOW_PROPOSAL_OPTION, "Add Drones to a Show Proposal", new ConfigShowPropUI()::show);
        menu.addItem(ADD_VIDEO_TO_SHOW_PROPOSAL_OPTION, "Add Video to Show Proposal", new AddVideoToProposalUI()::show);
        menu.addItem(CONFIGURE_TEMPLATE_OF_SHOW_PROPOSAL_OPTION, "Configure Template of Show Proposal", new ConfigureProposalDocumentUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private SubMenu buildCollaboratorShowProposalsMenu() {
        final SubMenu menu = new SubMenu("Show Proposals", SHOW_PROPOSALS_MENU_TITLE);
        menu.addItem(COLLABORATOR_CREATE_SHOW_PROPOSAL_OPTION, "Create a Show Proposal", new CreateShowProposalUI()::show);
        menu.addItem(COLLABORATOR_ADD_VIDEO_TO_SHOW_PROPOSAL_OPTION, "Add Video to Show Proposal", new AddVideoToProposalUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private SubMenu buildShowProposalsManagerMenu() {
        final SubMenu menu = new SubMenu("Show Proposals", SHOW_PROPOSALS_MENU_TITLE);

        menu.addItem(MANAGER_CONFIGURE_TEMPLATE_OF_SHOW_PROPOSAL_OPTION, "Configure Template of Show Proposal",
                new ConfigureProposalDocumentUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }
}
