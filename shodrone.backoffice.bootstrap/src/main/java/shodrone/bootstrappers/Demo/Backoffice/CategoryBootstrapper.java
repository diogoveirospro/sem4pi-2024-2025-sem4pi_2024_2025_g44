package shodrone.bootstrappers.Demo.Backoffice;

import core.Category.application.AddCategoryController;
import core.Category.domain.ValueObjects.CategoryName;
import core.Shared.domain.ValueObjects.Description;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.presentation.UtilsUI;

public class CategoryBootstrapper implements Action {

    private static final AddCategoryController controller = new AddCategoryController();
    private static final Logger LOGGER = LogManager.getLogger(CategoryBootstrapper.class);

    @Override
    public boolean execute() {

        registerCategory("category1", "description1");
        registerCategory("category2", "description2");
        registerCategory("category3", "description3");
        registerCategory("category4", "description4");
        registerCategory("category5", "description5");
        registerCategory("category6", "description6");

        return true;

    }

    private void registerCategory(String name, String description) {

        CategoryName categoryName = new CategoryName(name);
        Description categoryDescription = new Description(description);

        controller.addCategory(categoryName, categoryDescription);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Category registered: " + name + UtilsUI.RESET);

    }
}
