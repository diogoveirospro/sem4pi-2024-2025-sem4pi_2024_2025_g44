package Shodrone.console.Menu;

import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.menu.MenuItemRenderer;

public class HorizontalMenuRendererShodrone extends MenuRendererShodrone{

    public HorizontalMenuRendererShodrone(final Menu menu, final MenuItemRenderer itemRenderer) {
        super(menu, itemRenderer);
    }

    protected void doRender() {
        System.out.print("| ");

        for(MenuItem item : this.menu.itens()) {
            this.itemRenderer.render(item);
            System.out.print(" | ");
        }

    }
}

