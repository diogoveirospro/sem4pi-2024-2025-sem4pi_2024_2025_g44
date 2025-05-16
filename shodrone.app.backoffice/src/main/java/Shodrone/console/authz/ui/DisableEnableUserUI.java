package Shodrone.console.authz.ui;

import Shodrone.console.authz.printer.SystemUserPrinter;
import core.Category.domain.Entities.Category;
import core.User.application.DisableEnableUserController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class DisableEnableUserUI extends AbstractFancyListUI<SystemUser> {
    private final DisableEnableUserController controller = new DisableEnableUserController();

    @Override
    protected boolean doShow() {
        final Iterable<SystemUser> users = elements();
        if (!users.iterator().hasNext()) {
            System.out.println(emptyMessage());
            UtilsUI.goBackAndWait();
            return true;
        }

        List<SystemUser> userList = new ArrayList<>();
        int index = 1;
        System.out.println(listHeader());
        for (SystemUser user : users) {
            System.out.printf("%-5d | ", index++);
            elementPrinter().visit(user);
            userList.add(user);
        }

        int option = 0;

        do {
            option = UtilsUI.selectsIndex(userList);

            if (option == -2) {
                break;
            }

            if (option < 0 || option > userList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            }

        } while (option < 0 || option > userList.size());

        SystemUser selectedUser = userList.get(option);

        if (selectedUser == null) {
            System.out.println(UtilsUI.RED + "\nInvalid user selection." + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;
        }

        if (!selectedUser.isActive()) {
            controller.enableUser(selectedUser);
            System.out.println(UtilsUI.GREEN + "\nUser ENABLED successfully!" + UtilsUI.RESET);
        } else {
            controller.disableUser(selectedUser);
            System.out.println(UtilsUI.GREEN + "\nUser DISABLED successfully!" + UtilsUI.RESET);
        }

        UtilsUI.goBackAndWait();
        return true;
    }

    @Override
    protected Iterable<SystemUser> elements() {
        return controller.listAllUsers();
    }

    @Override
    protected Visitor<SystemUser> elementPrinter() {
        return new SystemUserPrinter();
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("%-5s | %-15s | %-15s | %-15s | %-10s |", "INDEX", "USERNAME", "F. NAME", "L. NAME", "STATUS") + "\n"
                + String.format("%-5s-+-%-15s-+-%-15s-+-%-15s-+-%-10s-+", "-".repeat(5),"-".repeat(15), "-".repeat(15),
                "-".repeat(15), "-".repeat(10))
                + UtilsUI.RESET;
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "\nNo Users Found!!" + UtilsUI.RESET;
    }

    @Override
    public String headline() {
        return "Disable and Enable Users";
    }
}
