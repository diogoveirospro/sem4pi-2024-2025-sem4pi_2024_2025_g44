package Shodrone;

import Shodrone.console.Menu.MainMenu;
import core.Persistence.PersistenceContext;
import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import plugins.PluginInitializer;
import shodrone.ShodroneBaseApplication;
import shodrone.authz.ui.LoginUI;
import shodrone.infrastructure.authz.AuthenticationCredentialHandler;

public class ShodroneBackoffice extends ShodroneBaseApplication {
    private ShodroneBackoffice () {

    }

    public static void main(String[] args) {
        PluginInitializer.initialize();
        new ShodroneBackoffice().run(args);
    }

    @Override
    protected void doMain(String[] args) {
        // login and go to main menu
        final boolean authenticated = new LoginUI(new AuthenticationCredentialHandler()).show();
        if (authenticated) {
            // go to main menu
            final var menu = new MainMenu();
            menu.mainLoop();
        }
    }

    @Override
    protected String appTitle() {
        return "Shodrone Backoffice";
    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new ShodronePasswordPolicy(),
                new PlainTextEncoder());
    }

    @Override
    protected void doSetupEventHandlers(EventDispatcher dispatcher) {

    }
}
