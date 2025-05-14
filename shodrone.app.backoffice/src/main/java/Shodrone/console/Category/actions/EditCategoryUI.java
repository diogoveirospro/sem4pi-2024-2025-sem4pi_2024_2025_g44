package Shodrone.console.Category.actions;

import Shodrone.exceptions.UserCancelledException;
import core.Category.application.EditCategoryController;
import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Shared.domain.ValueObjects.Description;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.List;

import static shodrone.presentation.UtilsUI.readLineFromConsole;

/**
 * User Interface for editing a category in the system.
 */
public class EditCategoryUI extends AbstractFancyUI {

    private final EditCategoryController controller = new EditCategoryController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Show the UI for editing a category.
     *
     * @return true if the category was edited successfully, false otherwise.
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

            System.out.println(UtilsUI.BOLD + "Select a category to edit:" + UtilsUI.RESET);
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).name().toString());
            }

            int choice = enterValidChoice(categories.size());
            Category selectedCategory = categories.get(choice - 1);

            CategoryName oldName = selectedCategory.name();

            CategoryName newName = enterValidCategoryName("Enter the new category name (or type 'cancel' to go back): ");
            Description description = enterValidDescription();

            controller.editCategory(oldName, newName, description);
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Category edited successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;

        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nOperation cancelled." + UtilsUI.RESET);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    private int enterValidChoice(int max) {
        int choice;
        do {
            try {
                String input = readLineFromConsole(UtilsUI.BOLD + "Enter the number of the category to edit: " + UtilsUI.RESET);
                choice = Integer.parseInt(input);

                if (choice < 1 || choice > max) {
                    throw new IllegalArgumentException("Invalid choice. Please select a number between 1 and " + max + ".");
                }

                return choice;
            } catch (NumberFormatException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please enter a valid number." + UtilsUI.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }

    /**
     * Display the headline for the UI.
     *
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "Edit Category";
    }

    /**
     * Enter a valid category name.
     *
     * @param prompt the prompt message to display.
     * @return a CategoryName object.
     */
    private CategoryName enterValidCategoryName(String prompt) {
        String name;
        do {
            try {
                name = readLineFromConsole(UtilsUI.BOLD + prompt + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(name)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                CategoryName newName = new CategoryName(name);

                // Check if the new name already exists and is not the same as the old name
                if (controller.categoryExists(newName)) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nA category with this name already exists. Please choose a different name." + UtilsUI.RESET);
                    continue;
                }

                return newName;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    /**
     * Enter a valid description for the category.
     *
     * @return a Description object.
     */
    private Description enterValidDescription() {
        String description;
        do {
            try {
                description = readLineFromConsole(UtilsUI.BOLD + "Enter the category description (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(description)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new Description(description);
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
}