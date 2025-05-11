package Shodrone.console.Category.printer;

import core.Category.application.ListExistingCategoriesController;
import core.Category.domain.Entities.Category;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.List;

/**
 * User Interface for listing all existing categories.
 */
public class ListExistingCategoriesUI extends AbstractFancyUI {

    private final ListExistingCategoriesController controller = new ListExistingCategoriesController();

    /**
     * Show the UI for listing categories.
     * @return true if the operation was successful, false otherwise.
     */
    @Override
    protected boolean doShow() {
        System.out.println(UtilsUI.BOLD + "Listing all categories:" + UtilsUI.RESET);

        try {
            List<Category> categories = controller.listExistingCategories();

            if (categories.isEmpty()) {
                System.out.println(UtilsUI.YELLOW + "No categories found." + UtilsUI.RESET);
            } else {
                for (Category category : categories) {
                    System.out.println(formatCategory(category));
                }
            }

            UtilsUI.goBackAndWait();
            return true;

        } catch (Exception e) {
            System.out.println(UtilsUI.RED + "An error occurred while listing categories: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    /**
     * Display the headline for the UI.
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "List Existing Categories";
    }

    /**
     * Format a category for display.
     * @param category the category to format.
     * @return a formatted string representing the category.
     */
    private String formatCategory(Category category) {
        return UtilsUI.BOLD + "Name: " + UtilsUI.RESET + category.name().toString() + "\n" +
               UtilsUI.BOLD + "Description: " + UtilsUI.RESET + category.description().toString() + "\n" +
               UtilsUI.BOLD + "Creation Date: " + UtilsUI.RESET + category.creationDate.toString() + "\n" +
               UtilsUI.BOLD + "Last Update Date: " + UtilsUI.RESET + (category.lastUpdateDate != null ? category.lastUpdateDate.toString() : "N/A") + "\n";
    }
}