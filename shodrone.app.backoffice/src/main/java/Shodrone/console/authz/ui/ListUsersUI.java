package Shodrone.console.authz.ui;

import core.User.domain.Entities.User;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListUsersUI extends AbstractListUI<User> {
    @Override
    protected Iterable<User> elements() {
        return null;
    }

    @Override
    protected Visitor<User> elementPrinter() {
        return null;
    }

    @Override
    protected String elementName() {
        return "";
    }

    @Override
    protected String listHeader() {
        return "";
    }

    @Override
    protected String emptyMessage() {
        return "";
    }

    @Override
    public String headline() {
        return "";
    }
}
