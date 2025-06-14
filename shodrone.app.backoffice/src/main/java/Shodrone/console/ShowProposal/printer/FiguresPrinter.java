package Shodrone.console.ShowProposal.printer;

import core.Category.domain.ValueObjects.CategoryStatus;
import core.Figure.domain.Entities.Figure;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class FiguresPrinter implements Visitor<Figure> {

    @Override
    public void visit(Figure visitee) {
        System.out.printf("%s - %s - %s |\n",
                visitee.identity().code().toString(),
                visitee.identity().version().toString(),
                visitee.description().toString()
        );
    }
}
