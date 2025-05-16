package Shodrone.console.Figure.printer;

import core.Category.domain.Entities.Category;
import eapli.framework.visitor.Visitor;

public class CategoriesPrinter implements Visitor<Category> {

    @Override
    public void visit(Category visitee) {
        System.out.printf("%s - %s", visitee.name(), visitee.description());
    }
}
