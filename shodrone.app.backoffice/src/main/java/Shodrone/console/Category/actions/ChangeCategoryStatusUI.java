package Shodrone.console.Category.actions;

import Shodrone.exceptions.UserCancelledException;
import core.Category.application.ChangeCategoryStatusController;
import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryStatus;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.List;

import static shodrone.presentation.UtilsUI.readLineFromConsole;

/**
 * User Interface for changing the status of a category.
 */
public class ChangeCategoryStatusUI extends AbstractFancyUI {

    private final ChangeCategoryStatusController controller = new ChangeCategoryStatusController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

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

        try {
            List<Category> categories = controller.listAllCategories();

            if (categories.isEmpty()) {
                System.out.println(UtilsUI.YELLOW + "No categories found." + UtilsUI.RESET);
                return false;
            }

            System.out.println(UtilsUI.BOLD + "Select a category to change its status:" + UtilsUI.RESET);
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).name().toString() + " (Status: " + categories.get(i).status().toString() + ")");
            }

            int choice = enterValidChoice(categories.size());
            Category selectedCategory = categories.get(choice - 1);

            CategoryStatus newStatus;
            do {
                System.out.println(UtilsUI.BOLD + "Select the new status for the category:" + UtilsUI.RESET);
                System.out.println("1- Active");
                System.out.println("2- Inactive");

                int statusChoice = enterValidChoice(2);
                newStatus = (statusChoice == 1) ? CategoryStatus.ACTIVE : CategoryStatus.INACTIVE;

                if (isValidStatus(selectedCategory, newStatus)) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nThe selected status is the same as the current status. Please choose a different status." + UtilsUI.RESET);
                }
            } while (isValidStatus(selectedCategory, newStatus));


            controller.changeCategoryStatus(selectedCategory.name(), newStatus);

            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Category status changed successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;

        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nOperation cancelled." + UtilsUI.RESET);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    /**
     * Validate if the selected status is different from the current status.
     *
     * @param category  the selected category.
     * @param newStatus the new status to validate.
     * @return true if the new status is valid, false otherwise.
     */
    private boolean isValidStatus(Category category, CategoryStatus newStatus) {
        return category.status().equals(newStatus);
    }

    /**
     * Prompt the user to enter a valid choice from the list.
     *
     * @param max the maximum valid choice.
     * @return the selected choice as an integer.
     */
    private int enterValidChoice(int max) {
        int choice;
        do {
            try {
                String input = readLineFromConsole(UtilsUI.BOLD + "Enter the number of the category to change its status: " + UtilsUI.RESET);
                choice = Integer.parseInt(input);

                if (choice < 1 || choice > max) {
                    throw new IllegalArgumentException("Invalid choice. Please select a number between 1 and " + max + ".");
                }

                return choice;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
            }
        } while (true);
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
        return "Change Category Status";
    }
}