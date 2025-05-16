package Shodrone.console.Category.printer;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryStatus;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class CategoryPrinter implements Visitor<Category> {
    @Override
    public void visit(final Category category) {
        String typePlain = String.format("%-10s", category.status().equals(CategoryStatus.ACTIVE) ? "Active" : "Inactive");
        String typeColored = category.status().equals(CategoryStatus.ACTIVE)
                ? UtilsUI.GREEN + typePlain + UtilsUI.RESET
                : UtilsUI.RED + typePlain + UtilsUI.RESET;

        System.out.printf(
                "%-15s | %s |\n",
                category.name().toString(),
                typeColored
        );
    }
}