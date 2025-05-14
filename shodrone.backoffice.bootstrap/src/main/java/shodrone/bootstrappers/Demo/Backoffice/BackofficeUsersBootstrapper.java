package shodrone.bootstrappers.Demo.Backoffice;

import core.Persistence.Application;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.ShodroneRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.Demo.UsersBootstrapperBase;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import shodrone.presentation.UtilsUI;

import java.util.HashSet;
import java.util.Set;

/**
 * Bootstrapper for Backoffice Users.
 */
public class BackofficeUsersBootstrapper extends UsersBootstrapperBase implements Action {

    private static final Logger LOGGER = LogManager.getLogger(BackofficeUsersBootstrapper.class);

    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD = "Password123";

    @Override
    public boolean execute() {
        registerAdmin("jarvis", PASSWORD, "Tony", "Stark", "tony.stark@showdrone.com", new PhoneNumber("+351", "123456789"));
        registerCollaborator("collaborator", PASSWORD, "Bruce", "Wayne", "bruce.wayne@showdrone.com", new PhoneNumber("+351", "123456789"));
        registerManager("manager", PASSWORD, "Diana", "Prince", "diana.prince@showdrone.com", new PhoneNumber("+351", "123456789"));
        registerShowDesigner("showdesigner", PASSWORD, "Peter", "Parker", "peter.parker@showdrone.com", new PhoneNumber("+351", "123456789"));
        registerDroneTech("dronetech", PASSWORD, "Clark", "Kent", "clark.kent@showdrone.com", new PhoneNumber("+351", "123456789"));
        return true;
    }

    private void registerAdmin(final String username, final String password,
                               final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        if (!email.contains("@" + Application.settings().shodroneDomain())) {
            throw new IllegalArgumentException("Admin email must be from the Shodrone domain");
        }

        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.ADMIN);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Admin registered: {}" + UtilsUI.RESET, username);
    }

    private void registerCollaborator(final String username, final String password,
                                      final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        if (!email.contains("@" + Application.settings().shodroneDomain())) {
            throw new IllegalArgumentException("Collaborator email must be from the Shodrone domain");
        }

        final Set<Role> roles = Set.of(ShodroneRoles.COLLABORATOR, ShodroneRoles.USER);
        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Collaborator registered: {}" + UtilsUI.RESET, username);
    }

    private void registerManager(final String username, final String password,
                                 final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        if (!email.contains("@" + Application.settings().shodroneDomain())) {
            throw new IllegalArgumentException("Manager email must be from the Shodrone domain");
        }

        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.MANAGER);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Manager registered: {}" + UtilsUI.RESET, username);
    }

    private void registerShowDesigner(final String username, final String password,
                                      final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        if (!email.contains("@" + Application.settings().shodroneDomain())) {
            throw new IllegalArgumentException("Show Designer email must be from the Shodrone domain");
        }

        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.SHOWDESIGNER);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Show Designer registered: {}" + UtilsUI.RESET, username);
    }

    private void registerDroneTech(final String username, final String password,
                                   final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        if (!email.contains("@" + Application.settings().shodroneDomain())) {
            throw new IllegalArgumentException("Drone Tech email must be from the Shodrone domain");
        }

        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.DRONETECH);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Drone Tech registered: {}" + UtilsUI.RESET, username);
    }
}