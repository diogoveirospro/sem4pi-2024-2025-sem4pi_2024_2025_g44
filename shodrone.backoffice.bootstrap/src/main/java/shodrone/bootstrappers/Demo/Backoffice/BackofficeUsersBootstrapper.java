package shodrone.bootstrappers.Demo.Backoffice;

import core.Persistence.Application;
import core.Shared.domain.ValueObjects.PhoneNumber;
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
    private static final String PASSWORD = "Password123";

    @Override
    public boolean execute() {
        registerAdmin("jarvis", PASSWORD, "Tony", "Stark", "tony.stark@avengers.com", new PhoneNumber("+351", "123456789"));
        registerCollaborator("collaborator", PASSWORD, "Bruce", "Wayne", "bruce.wayne@shodrone.com", new PhoneNumber("+351", "123456789"));
        registerManager("manager", PASSWORD, "Diana", "Prince", "diana.prince@themyscira.com", new PhoneNumber("+351", "123456789"));
        registerShowDesigner("showdesigner", PASSWORD, "Peter", "Parker", "peter.parker@dailybugle.com", new PhoneNumber("+351", "123456789"));
        registerDroneTech("dronetech", PASSWORD, "Clark", "Kent", "clark.kent@dailyplanet.com", new PhoneNumber("+351", "123456789"));
        return true;
    }

    private void registerAdmin(final String username, final String password,
                               final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.ADMIN);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
    }

    private void registerCollaborator(final String username, final String password,
                                      final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        if (!email.contains("@" + Application.settings().shodroneDomain())) {
            throw new IllegalArgumentException("O domínio do e-mail deve ser 'shodrone'");
        }

        final Set<Role> roles = Set.of(ShodroneRoles.COLLABORATOR, ShodroneRoles.USER);
        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
    }

    private void registerManager(final String username, final String password,
                                 final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.MANAGER);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
    }

    private void registerShowDesigner(final String username, final String password,
                                      final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.SHOWDESIGNER);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
    }

    private void registerDroneTech(final String username, final String password,
                                   final String firstName, final String lastName, final String email, final PhoneNumber phoneNumber) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ShodroneRoles.DRONETECH);
        roles.add(ShodroneRoles.USER);

        registerUser(username, password, firstName, lastName, email, roles, phoneNumber);
    }
}