package Shodrone.console.Menu;

import eapli.framework.actions.menu.Menu;

public class SubMenu extends Menu {

    private String bigTitle;

    public SubMenu() {
        super("");
    }

    public SubMenu(final String title, final String bigTitle) {
        super(title);
        this.bigTitle = bigTitle;
    }

    public String bigTitle() {
        return this.bigTitle;
    }
}

