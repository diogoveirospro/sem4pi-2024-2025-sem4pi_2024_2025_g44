package Shodrone.console.Menu;

import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.menu.MenuItemRenderer;

public class VerticalMenuRendererShodrone extends MenuRendererShodrone {

    public VerticalMenuRendererShodrone(final Menu menu, final MenuItemRenderer itemRenderer) {
        super(menu, itemRenderer);
    }

    protected void doRender() {
        for(MenuItem item : this.menu.itens()) {
            this.itemRenderer.render(item);
            System.out.println();
        }

    }
}
