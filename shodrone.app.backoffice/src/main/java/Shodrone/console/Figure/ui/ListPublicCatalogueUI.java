package Shodrone.console.Figure.ui;

import core.Figure.application.ListPublicCatalogueController;
import core.Figure.domain.Entities.Figure;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListPublicCatalogueUI extends AbstractListUI {

    ListPublicCatalogueController controller = new ListPublicCatalogueController();

    @Override
    protected Iterable<Figure> elements() {
        return controller.listPublicCatalogue();
    }

    @Override
    protected Visitor<Figure> elementPrinter() {
        return figure -> System.out.printf("Code: %s | Version: %s | Description: %s%n",
                figure.identity().code(), figure.identity().version(), figure.description());
    }


    @Override
    protected String elementName() {
        return "Figure";
    }

    @Override
    protected String listHeader() {
        return "All Active Public Figures";
    }

    @Override
    protected String emptyMessage() {
        return "No Active Public Figures found";
    }

    @Override
    public String headline() {
        return "List Public Catalogue";
    }
}
