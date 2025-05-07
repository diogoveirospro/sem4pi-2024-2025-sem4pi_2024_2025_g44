package shodrone.bootstrappers.Demo.Backoffice;

import core.User.domain.ShodroneRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.Demo.UsersBootstrapperBase;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Bootstrapper for Backoffice Users.
 */
public class BackofficeUsersBootstrapper extends UsersBootstrapperBase implements Action {

    private static final Logger LOGGER = LogManager.getLogger(BackofficeUsersBootstrapper.class);

    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD = "12345678";

    @Override
    public boolean execute() {
        registerAdmin("admin", PASSWORD, "Omni", "Man", "omniman@gmail.com");
        registerCollaborator("collaborator", PASSWORD, "John", "Doe", "johndoe@gmail.com");
        registerManager("manager", PASSWORD, "Jane", "Doe", "janedoe@gmail.com");
        return true;
    }

    private void registerAdmin(final String username, final String password,
                               final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.ADMIN);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerCollaborator(final String username, final String password,
                                      final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.COLLABORATOR);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerManager(final String username, final String password,
                                 final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.MANAGER);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}