package core.User.application;

import java.util.*;


import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.Entities.ShodroneUser;
import core.User.domain.ShodroneRoles;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.time.util.CurrentTimeCalendars;

@UseCaseController
public class RegisterUsersController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();

    public Role[] getRoleTypes() {
        return ShodroneRoles.nonUserValues();
    }

    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName,
                              final String email, final Set<Role> roles, final Calendar createdOn, PhoneNumber phoneNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(ShodroneRoles.POWER_USER, ShodroneRoles.ADMIN);

        SystemUser user = userSvc.registerNewUser(username, password, firstName, lastName, email, roles,
                createdOn);
        ShodroneUser shodroneUser = new ShodroneUser(user, phoneNumber);
        userRepository.save(shodroneUser);

        return user;
    }

    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName,
                              final String email, final Set<Role> roles, PhoneNumber phoneNumber) {
        return addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now(), phoneNumber);
    }

    public boolean isUsernameTaken(String username) {
        Iterable<SystemUser> allUsers = userSvc.allUsers();
        for (SystemUser user : allUsers) {
            if (user.username().equals(Username.valueOf(username))) {
                return true; // Nome de utilizador já existe
            }
        }
        return false; // Nome de utilizador disponível
    }

    public List<String> availableCountries() {
        Map<String, String> map;
        map = PhoneNumber.countryCodes();
        List<String> countries = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            countries.add(entry.getValue());
        }
        return countries;
    }

    public String countryCode(String country) {
        return PhoneNumber.countryCodeOfCountry(country);
    }
}
