package Shodrone.console.Figure.printer;

import eapli.framework.visitor.Visitor;

public class FileNamePrinter implements Visitor<String> {

    @Override
    public void visit(String visitee) {
        String fileName = visitee.substring(visitee.lastIndexOf("\\") + 1);
        System.out.printf("%s", fileName);
    }
}
