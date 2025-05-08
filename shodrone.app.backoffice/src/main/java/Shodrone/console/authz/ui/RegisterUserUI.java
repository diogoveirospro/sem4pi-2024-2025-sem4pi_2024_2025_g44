package Shodrone.console.authz.ui;

import core.User.application.RegisterUsersController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import shodrone.presentation.UtilsUI;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("java:S106")
public class RegisterUserUI extends AbstractUI {

    private final RegisterUsersController theController = new RegisterUsersController();

    @Override
    protected boolean doShow() {


        final String username = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Username: " + UtilsUI.RESET);
        final String password = UtilsUI.readPassword(UtilsUI.BOLD + "Password: " + UtilsUI.RESET);
        final String firstName = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "First Name: " + UtilsUI.RESET);
        final String lastName = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Last Name: " + UtilsUI.RESET);
        final String email = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "E-Mail: " + UtilsUI.RESET);

        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        try {
            this.theController.addUser(username, password, firstName, lastName, email, roleTypes);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("That username is already in use.");
        }

        return false;
    }

    private boolean showRoles(final Set<Role> roleTypes) {

        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : theController.getRoleTypes()) {
            rolesMenu.addItem(
                    MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Register User";
    }
}
