package Shodrone.console.Menu;

import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import shodrone.presentation.UtilsUI;

public class MenuRendererShodrone extends MenuRenderer{

    protected MenuRendererShodrone(Menu menu, MenuItemRenderer itemRenderer) {
        super(menu, itemRenderer);
    }

    @Override
    protected void doRender() {

    }

    @Override
    protected boolean doRenderSubmenu(final Menu submenu) {
        if (!(submenu instanceof SubMenu)) {
            throw new IllegalArgumentException("Submenu must be of type SubMenu");
        }

        SubMenu sub = (SubMenu) submenu;
        MenuRenderer renderer = new VerticalMenuRenderer(submenu, this.itemRenderer);
        UtilsUI.clearConsole();
        System.out.println("\n\n " + UtilsUI.BOLD + UtilsUI.BLUE + sub.bigTitle() + UtilsUI.RESET);
        renderer.render();
        return false;
    }

}
