package Shodrone.console.authz.ui;

import Shodrone.console.authz.printer.SystemUserPrinter;
import core.Figure.domain.Entities.Figure;
import core.User.application.ListUsersController;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

@SuppressWarnings({ "squid:S106" })
public class ListUsersUI extends AbstractFancyListUI<SystemUser> {
    private final ListUsersController theController = new ListUsersController();

    /**
     * Method to show the UI.
     * @return true if the UI was shown successfully
     */
    @Override
    public boolean doShow() {
        final Iterable<SystemUser> users = elements();
        if (!users.iterator().hasNext()) {
            System.out.println(emptyMessage());
            UtilsUI.goBackAndWait();
            return true;
        }

        System.out.println(listHeader());
        for (SystemUser user : users) {
            elementPrinter().visit(user);
        }

        UtilsUI.goBackAndWait();
        return true;
    }

    /**
     * Prints the details of each system user in the system.
     * @return Visitor that prints the details of each system user
     */
    @Override
    protected Visitor<SystemUser> elementPrinter() {
        return new SystemUserPrinter();
    }

    /**
     * Returns the name of the element to be printed.
     * @return the name of the element
     */
    @Override
    protected String elementName() {
        return "";
    }

    /**
     * Returns the header for the list of users.
     * @return the header for the list
     */
    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("%-15s | %-15s | %-15s | %-10s |", "USERNAME", "F. NAME", "L. NAME", "STATUS") + "\n"
                + String.format("%-15s-+-%-15s-+-%-15s-+-%-10s-+", "-".repeat(15), "-".repeat(15),
                "-".repeat(15), "-".repeat(10))
                + UtilsUI.RESET;
    }

    /**
     * Returns the message to be displayed when there are no users in the system.
     * @return the message to be displayed
     */
    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "No Users Found!!" + UtilsUI.RESET;
    }

    /**
     * Returns the headline for the UI.
     * @return the headline string
     */
    @Override
    public String headline() {
        return "List Users";
    }

    /**
     * Returns the iterable of system users to be displayed.
     * @return the iterable of system users
     */
    @Override
    protected Iterable<SystemUser> elements() {
        return theController.allUsers();
    }

}
