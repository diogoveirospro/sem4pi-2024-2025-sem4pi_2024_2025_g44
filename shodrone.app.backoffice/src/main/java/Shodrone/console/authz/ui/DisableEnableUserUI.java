package Shodrone.console.authz.ui;

import core.User.application.DisableEnableUserController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class DisableEnableUserUI extends AbstractUI {
    private final DisableEnableUserController controller = new DisableEnableUserController();

    @Override
    protected boolean doShow() {
        List<SystemUser> users = new ArrayList<>();
        controller.listAllUsers().forEach(users::add);

        if (users == null || !users.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + "No users available." + UtilsUI.RESET);
            return false;
        }

        displayUsers(users);

        int option = UtilsUI.selectsIndex(users);
        if (option == -2) {
            System.out.println(UtilsUI.RED + "Selection cancelled." + UtilsUI.RESET);
            return false;
        }

        SystemUser selectedUser = getUserAtIndex(users, option);
        if (selectedUser == null) {
            System.out.println(UtilsUI.RED + "Invalid user selection." + UtilsUI.RESET);
            return false;
        }

        System.out.println("Current status: " + (selectedUser.isActive() ? "Active" : "Inactive"));
        String action = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Do you want to enable or disable this user? (enter 'enable' or 'disable'): " + UtilsUI.RESET);

        if ("enable".equalsIgnoreCase(action)) {
            controller.enableUser(selectedUser);
            System.out.println(UtilsUI.GREEN + "User enabled successfully!" + UtilsUI.RESET);
        } else if ("disable".equalsIgnoreCase(action)) {
            controller.disableUser(selectedUser);
            System.out.println(UtilsUI.RED + "User disabled successfully!" + UtilsUI.RESET);
        } else {
            System.out.println(UtilsUI.RED + "Invalid option. Operation cancelled." + UtilsUI.RESET);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Disable/Enable Users";
    }

    private void displayUsers(Iterable<SystemUser> users) {
        System.out.println("+= List Users ================");
        System.out.printf("%-10s %-20s %-20s %-10s\n", "#", "USERNAME", "F. NAME", "L. NAME", "STATUS");

        int index = 1;
        for (SystemUser user : users) {
            System.out.printf("%-10d %-20s %-20s %-10s\n", index++, user.username(), user.name().firstName(), user.name().lastName(), user.isActive() ? "ACTIVE" : "INACTIVE");
        }

        System.out.println("+----------------------------+");
    }

    private SystemUser getUserAtIndex(Iterable<SystemUser> users, int index) {
        int currentIndex = 0;
        for (SystemUser user : users) {
            if (currentIndex == index) {
                return user;
            }
            currentIndex++;
        }
        return null;
    }
}
