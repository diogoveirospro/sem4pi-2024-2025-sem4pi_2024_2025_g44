package core.User.repositories;

import core.Shared.domain.ValueObjects.Email;
import core.User.domain.Entities.ShodroneUser;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface ShodroneUserRepository extends DomainRepository<Username, ShodroneUser> {

    /**
     * Returns a ShodroneUser with the given username.
     *
     * @param username the username of the ShodroneUser
     * @return the ShodroneUser with the given username
     */
    ShodroneUser findByUsername(Username username);
    ShodroneUser findByEmail(Email email);
}
