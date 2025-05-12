package Shodrone.console.Figure.printer;

import core.Figure.domain.Entities.Figure;
import eapli.framework.visitor.Visitor;

public class FiguresPrinter implements Visitor<Figure> {

    @Override
    public void visit(Figure visitee) {
        System.out.printf("%s - %s - %s", visitee.figureID().code(), visitee.figureID().version(), visitee.description());
    }
}
