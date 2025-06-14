package Shodrone.console.Figure.printer;

import core.Figure.domain.Entities.Figure;
import eapli.framework.visitor.Visitor;

public class AnotherFiguresPrinter implements Visitor<Figure> {
    @Override
    public void visit(Figure visitee) {
        System.out.printf("%s - %s",
                visitee.identity().code().toString(),
                visitee.identity().version().toString()
        );
    }
}
