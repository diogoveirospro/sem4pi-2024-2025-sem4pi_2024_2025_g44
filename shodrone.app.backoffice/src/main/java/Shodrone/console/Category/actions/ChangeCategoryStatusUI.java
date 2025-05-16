package Shodrone.console.Category.actions;

import Shodrone.console.Category.printer.CategoryPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Category.application.ChangeCategoryStatusController;
import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryStatus;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

import static shodrone.presentation.UtilsUI.readLineFromConsole;

/**
 * User Interface for changing the status of a category.
 */
public class ChangeCategoryStatusUI extends AbstractFancyListUI<Category> {

    private final ChangeCategoryStatusController controller = new ChangeCategoryStatusController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected Iterable<Category> elements() {
        return controller.listAllCategories();
    }

    @Override
    protected Visitor<Category> elementPrinter() {
        return new CategoryPrinter();
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String listHeader() {
        return UtilsUI.BOLD
                + String.format("%-5s | %-15s | %-10s |", "INDEX", "CATEGORY NAME", "STATUS") + "\n"
                + String.format("%-5s-+-%-15s-+-%-10s-+", "-".repeat(5),"-".repeat(15), "-".repeat(10))
                + UtilsUI.RESET;
    }

    @Override
    protected String emptyMessage() {
        return UtilsUI.RED + UtilsUI.BOLD + "\nNo Categories Found!!" + UtilsUI.RESET;
    }

    /**
     * Show the UI for changing a category's status.
     *
     * @return true if the status was changed successfully, false otherwise.
     */
    @Override
    protected boolean doShow() {
        if (!isShowDesigner()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Access denied. Only Show Designers can perform this action." + UtilsUI.RESET);
            return false;
        }

        final Iterable<Category> categories = elements();
        if (!categories.iterator().hasNext()) {
            System.out.println(emptyMessage());
            UtilsUI.goBackAndWait();
            return true;
        }

        List<Category> categoryList = new ArrayList<>();
        int index = 1;
        System.out.println(listHeader());
        for (Category category : categories) {
            System.out.printf("%-5d | ", index++);
            elementPrinter().visit(category);
            categoryList.add(category);
        }

        int option = 0;

        do {
            option = UtilsUI.selectsIndex(categoryList);

            if (option == -2) {
                return false;
            }

            if (option < 0 || option > categoryList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            }

        } while (option < 0 || option > categoryList.size());

        Category selectedCategory = categoryList.get(option);

        if (selectedCategory == null) {
            System.out.println(UtilsUI.RED + "\nInvalid category selection." + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;
        }

        if (selectedCategory.status().equals(CategoryStatus.INACTIVE)) {
            controller.changeCategoryStatus(selectedCategory.name(), CategoryStatus.ACTIVE);
            System.out.println(UtilsUI.GREEN + "\nCategory ENABLED successfully!" + UtilsUI.RESET);
        } else {
            controller.changeCategoryStatus(selectedCategory.name(), CategoryStatus.INACTIVE);
            System.out.println(UtilsUI.GREEN + "\nCategory DISABLED successfully!" + UtilsUI.RESET);
        }

        UtilsUI.goBackAndWait();
        return true;
    }

    /**
     * Check if the current user is a Show Designer.
     *
     * @return true if the user is a Show Designer, false otherwise.
     */
    private boolean isShowDesigner() {
        return authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.SHOWDESIGNER);
    }

    /**
     * Display the headline for the UI.
     *
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "Activate and Deactivate Category";
    }
}